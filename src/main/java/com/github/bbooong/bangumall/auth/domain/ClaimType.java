package com.github.bbooong.bangumall.auth.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClaimType {
    MEMBER_ID("sub"),
    ROLE("role"),
    ;

    private final String claimName;
}
