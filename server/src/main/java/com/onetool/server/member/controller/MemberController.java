package com.onetool.server.member.controller;

import com.onetool.server.global.auth.login.PrincipalDetails;
import com.onetool.server.member.domain.Member;
import com.onetool.server.member.dto.*;
import com.onetool.server.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.net.URI;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody LoginRequest request
    ) {
        String token = memberService.login(request);

        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<MemberCreateResponse> createMember(@RequestBody MemberCreateRequest request) {
        MemberCreateResponse response = memberService.createMember(request);
        return ResponseEntity.created(URI.create("/users/" + response.id())).body(response);
    }

    @PostMapping("/emails/verification-requests")
    public ResponseEntity sendMessage(@RequestParam("email") @Valid String email) {
        memberService.sendCodeToEmail(email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/emails/verifications")
    public ResponseEntity verificationEmail(@RequestParam("email") @Valid String email,
                                            @RequestParam("code") String authCode) {
        boolean response = memberService.verifiedCode(email, authCode);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/password")
    public ResponseEntity findPwdCheck(@RequestBody MemberFindPwdRequest request) {
        boolean successFlag = memberService.findLostPwd(request);
        if(successFlag) {
            return ResponseEntity.ok("이메일을 발송했습니다.");
        } else {
            return ResponseEntity.badRequest().body("이메일 발송 과정에서 오류가 발생했습니다.");
        }
    }

    @PutMapping
    public ResponseEntity<String> updateMember(
            @RequestBody MemberUpdateRequest request,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long id = principalDetails.getContext().getId();
        memberService.updateMember(id, request);

        return ResponseEntity.ok("회원 정보가 수정되었습니다.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMember(
            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long id = principalDetails.getContext().getId();
        memberService.deleteMember(id);

        return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
    }

    @PostMapping("/email")
    public  ResponseEntity findEmail(@RequestBody MemberFindEmailRequest request) {
        String email = memberService.findEmail(request);
        return ResponseEntity.ok(email);
    }

    @GetMapping
    public ResponseEntity<MemberInfoResponse> getMemberInfo(
            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long id = principalDetails.getContext().getId();

        try {
            MemberInfoResponse memberResponse = memberService.getMemberInfo(id);
            return ResponseEntity.ok(memberResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}