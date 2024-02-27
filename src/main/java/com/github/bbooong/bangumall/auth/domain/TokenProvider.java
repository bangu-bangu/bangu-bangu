package com.github.bbooong.bangumall.auth.domain;

public interface TokenProvider {

    String generateAccessToken(final Long memberId);
}
