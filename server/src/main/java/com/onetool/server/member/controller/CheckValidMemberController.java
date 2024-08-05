package com.onetool.server.member.controller;

import com.onetool.server.global.auth.login.PrincipalDetails;
import com.onetool.server.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class CheckValidMemberController {
    private final MemberRepository memberRepository;
    @GetMapping("/check")
    public Boolean checkValidMember(@AuthenticationPrincipal PrincipalDetails principal) {
        return memberRepository.findByEmail(principal.getContext().getEmail()).isPresent();
    }
}