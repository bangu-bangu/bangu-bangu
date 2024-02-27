package com.github.bbooong.bangumall.auth.infrastructure;

import com.github.bbooong.bangumall.auth.domain.ClaimType;
import com.github.bbooong.bangumall.auth.domain.MemberRole;
import com.github.bbooong.bangumall.auth.domain.TokenProvider;
import io.jsonwebtoken.JwtBuilder;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenProviderImpl implements TokenProvider {

    private static final long TOKEN_EXPIRATION_MILLIS = TimeUnit.HOURS.toMillis(1);

    private final JwtBuilder builder;

    @Override
    public String generateToken(final Long id, final MemberRole role) {
        return builder.claim(ClaimType.MEMBER_ID.getClaimName(), String.valueOf(id))
                .claim(ClaimType.ROLE.getClaimName(), MemberRole.VENDOR)
                .expiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_MILLIS))
                .compact();
    }
}
