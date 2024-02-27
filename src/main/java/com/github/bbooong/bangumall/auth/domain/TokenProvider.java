package com.github.bbooong.bangumall.auth.domain;

public interface TokenProvider {

    String generateToken(final Long memberId, final MemberRole role);
}
