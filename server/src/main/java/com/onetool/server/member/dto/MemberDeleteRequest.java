package com.onetool.server.member.dto;

import com.onetool.server.member.domain.Member;
import lombok.Builder;

@Builder
public record MemberDeleteRequest(
        Long id,
        String password
) {

    public MemberDeleteRequest(Long id, String password) {
        this.id =id;
        this.password = password;
    }

    public static MemberDeleteRequest fromMember(Member member) {
        return new MemberDeleteRequest(
                member.getId(),
                member.getPassword()
        );
    }
}