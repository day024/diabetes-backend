package com.onetool.server.member.dto;

import com.onetool.server.member.domain.Member;
import jakarta.validation.constraints.Past;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MemberInfoResponse(
        String email,
        String password,
        String name,
        @Past LocalDate birthDate,
        String development_field,
        String phoneNum,
        boolean isNative,
        boolean service_accept,
        LocalDate user_registered_at
) {
    @Builder
    public MemberInfoResponse(String email, String password, String name, @Past LocalDate birthDate, String development_field, String phoneNum, boolean isNative, boolean service_accept, LocalDate user_registered_at) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.development_field = development_field;
        this.phoneNum = phoneNum;
        this.isNative = isNative;
        this.service_accept = service_accept;
        this.user_registered_at = user_registered_at;
    }

    public static MemberInfoResponse fromEntity(Member member) {
        return new MemberInfoResponse(
                member.getEmail(),
                member.getPassword(),
                member.getName(),
                member.getBirthDate(),
                member.getField(),
                member.getPhoneNum(),
                member.isNative(),
                member.isServiceAccept(),
                member.getUser_registered_at()
        );
    }
}