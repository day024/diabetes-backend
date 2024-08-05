package com.onetool.server.member.service;

import com.onetool.server.global.auth.MemberAuthContext;
import com.onetool.server.global.auth.jwt.JwtUtil;
import com.onetool.server.global.exception.BusinessLogicException;
import com.onetool.server.global.exception.DuplicateMemberException;
import com.onetool.server.global.exception.MemberNotFoundException;
import com.onetool.server.global.redis.RedisService;
import com.onetool.server.mail.MailService;
import com.onetool.server.member.domain.DateTimeFormat;
import com.onetool.server.member.dto.*;
import com.onetool.server.member.repository.MemberRepository;
import com.onetool.server.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private static final String AUTH_CODE_PREFIX = "AuthCode ";

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    private final MailService mailService;
    private final RedisService redisService;

    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;

    public MemberCreateResponse createMember(MemberCreateRequest request) {
        Member member = memberRepository.save(request.toEntity(encoder.encode(request.password())));
        log.info("회원가입됨:" + member.getEmail());
        return MemberCreateResponse.of(member);
    }

    public String login(LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        log.info("============== 로그인 유저 정보 ===============");
        log.info(member.getEmail());
        log.info(member.getPassword());

        if(!encoder.matches(password, member.getPassword())){
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        MemberAuthContext context = MemberAuthContext.builder()
                .id(member.getId())
                .role(member.getRole().name())
                .name(member.getName())
                .email(member.getEmail())
                .password(member.getPassword())
                .build();

        return jwtUtil.create(context);
    }

    public void sendCodeToEmail(String toEmail) {
        this.checkDuplicatedEmail(toEmail); // 존재하면
        String title = "Onetool 이메일 인증 번호";
        String authCode = this.createCode();
        mailService.sendEmail(toEmail, title, authCode);
        // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = "AuthCode " + Email / value = AuthCode )
        redisService.setValues(AUTH_CODE_PREFIX + toEmail,
                authCode, Duration.ofMillis(this.authCodeExpirationMillis));
    }

    private void checkDuplicatedEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            log.debug("MemberServiceImpl.checkDuplicatedEmail exception occur email: {}", email);
            throw new DuplicateMemberException();
        }
    }

    private String createCode() {
        int length = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            log.debug("MemberService.createCode() exception occur");
            throw new BusinessLogicException();
        }
    }

    public boolean verifiedCode(String email, String authCode) {
        this.checkDuplicatedEmail(email);
        String redisAuthCode = redisService.getValues(AUTH_CODE_PREFIX + email);

        return redisService.checkExistsValue(redisAuthCode) && redisAuthCode.equals(authCode);
    }

    public boolean findLostPwd(MemberFindPwdRequest request) {
        String email = request.getEmail();
        Member member =  memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        String newPwd = createRandomPassword();
        member.setPassword(encoder.encode(newPwd));
        memberRepository.save(member);

        mailService.sendEmail(
                member.getEmail(),
                "원툴 비밀번호 찾기",
                newPwd
        );
        return true;
    }

    public void updateMember(Long id, MemberUpdateRequest request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        member.updateWith(request);
        if(request.getNewPassword() != null){
            member.updatePassword(encoder.encode(request.getNewPassword()));
        }

        memberRepository.save(member);
    }

    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        memberRepository.delete(member);
    }

    private String createRandomPassword() {
        int length = 15;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            log.debug("MemberService.createRandomPassword() exception occur");
            throw new BusinessLogicException();
        }
    }

    public String findEmail(MemberFindEmailRequest request) {
        String name = request.name();
        LocalDate birthDate = LocalDate.parse(request.birth_date(), DateTimeFormat.dateFormat);
        Member member = memberRepository.findByNameAndBirthDate(name, birthDate)
                .orElseThrow(MemberNotFoundException::new);
        return member.getEmail();
    }

    public MemberInfoResponse getMemberInfo(Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("회원 정보가 존재하지 않습니다."));

        return MemberInfoResponse.fromEntity(member);
    }
}