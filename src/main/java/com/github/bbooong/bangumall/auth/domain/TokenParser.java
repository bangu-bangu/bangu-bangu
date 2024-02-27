package com.github.bbooong.bangumall.auth.domain;

public interface TokenParser {

    Token parse(final String token);
}
