package com.github.bbooong.bangumall.auth.domain;

public enum MemberRole {
    MEMBER,
    ;

    public boolean matches(final String role) {
        return name().equals(role);
    }
}
