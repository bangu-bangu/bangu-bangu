package com.github.bbooong.bangumall.auth.application;

import com.github.bbooong.bangumall.auth.application.dto.AuthLoginRequest;
import com.github.bbooong.bangumall.auth.application.dto.AuthLoginResponse;
import com.github.bbooong.bangumall.auth.domain.Member;
import com.github.bbooong.bangumall.auth.domain.MemberRepository;
import com.github.bbooong.bangumall.auth.domain.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public AuthLoginResponse login(final AuthLoginRequest request) {
        final Member member =
                memberRepository
                        .findByEmailAndPassword(request.email(), request.password())
                        .orElseThrow(); // TODO: 예외 처리

        final String token = tokenProvider.generateToken(member.getId(), member.getRole());

        return new AuthLoginResponse(token);
    }
}
