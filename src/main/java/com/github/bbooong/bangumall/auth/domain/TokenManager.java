package com.github.bbooong.bangumall.auth.domain;

import org.springframework.stereotype.Component;

@Component
public class TokenManager {

    public String generateToken(final long subject) {
        return "1";
    }
}
