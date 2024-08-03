package com.onetool.server.global.auth;

import lombok.Getter;

@Getter
public class WhiteListVO {
    private final String[] AUTH_WHITELIST = {
            "/users/**", "/login/**", "/food/**", "/oauth2/**", "/diabetes/**"
    };
}

