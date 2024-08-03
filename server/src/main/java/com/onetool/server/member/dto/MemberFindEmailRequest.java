package com.onetool.server.member.dto;

import lombok.Data;

public record MemberFindEmailRequest(
        String name,
        String birth_date
) {

}
