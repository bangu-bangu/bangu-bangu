package com.github.bbooong.bangumall.auth.application;

import com.github.bbooong.bangumall.auth.application.dto.AuthLoginRequest;
import com.github.bbooong.bangumall.auth.application.dto.AuthLoginResponse;
import com.github.bbooong.bangumall.auth.domain.Member;
import com.github.bbooong.bangumall.auth.domain.MemberRepository;
import com.github.bbooong.bangumall.auth.domain.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final TokenManager tokenManager;

    public AuthLoginResponse login(final AuthLoginRequest request) {
        final Member member =
                memberRepository
                        .findByEmailAndPassword(request.email(), request.password())
                        .orElseThrow();

        final String token = tokenManager.generateToken(member.getId());
        return new AuthLoginResponse(token);
    }
}
