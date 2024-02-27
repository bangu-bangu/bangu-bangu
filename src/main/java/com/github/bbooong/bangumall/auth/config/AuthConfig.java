package com.github.bbooong.bangumall.auth.config;

import com.github.bbooong.bangumall.auth.domain.TokenParser;
import com.github.bbooong.bangumall.auth.domain.TokenProvider;
import com.github.bbooong.bangumall.auth.infrastructure.TokenParserImpl;
import com.github.bbooong.bangumall.auth.infrastructure.TokenProviderImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {

    @Bean
    public TokenProvider jwtProvider(@Value("${jwt.secret}") final String secret) {
        return new TokenProviderImpl(
                Jwts.builder().signWith(Keys.hmacShaKeyFor(secret.getBytes())));
    }

    @Bean
    public TokenParser jwtParser(@Value("${jwt.secret}") final String secret) {
        return new TokenParserImpl(
                Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build());
    }
}
