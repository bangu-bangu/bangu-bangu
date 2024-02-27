package com.github.bbooong.bangumall.auth.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.bbooong.bangumall.auth.domain.MemberRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("단위 테스트: TokenProviderImpl")
class TokenProviderImplTest {

    static final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor("asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf".getBytes());

    TokenProviderImpl jwtProviderImpl;

    @BeforeEach
    void setUp() {
        jwtProviderImpl = new TokenProviderImpl(Jwts.builder().signWith(SECRET_KEY));
    }

    @Test
    @DisplayName("JWT를 생성한다.")
    void createToken() {
        /* given */
        final Long memberId = 777L;
        final MemberRole memberRole = MemberRole.CUSTOMER;

        /* when */
        final String token = jwtProviderImpl.generateToken(memberId, memberRole);

        /* then */
        assertThat(token).isNotEmpty();
    }
}
