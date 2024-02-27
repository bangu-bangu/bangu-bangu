package com.github.bbooong.bangumall.auth.config;

import com.github.bbooong.bangumall.auth.application.dto.AuthPrincipal;
import com.github.bbooong.bangumall.auth.domain.Authorities;
import com.github.bbooong.bangumall.auth.domain.Token;
import com.github.bbooong.bangumall.auth.domain.TokenParser;
import com.github.bbooong.bangumall.auth.exception.JwtInvalidException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private static final String AUTH_PRINCIPAL = "authPrincipal";

    private final TokenParser tokenParser;

    @Override
    public boolean preHandle(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Object handler)
            throws Exception {
        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Authorities authorities = handlerMethod.getMethodAnnotation(Authorities.class);
        if (authorities == null) {
            return true;
        }

        final Token token = tokenParser.parse(request.getHeader(HttpHeaders.AUTHORIZATION));
        if (Arrays.stream(authorities.roles()).noneMatch(token::hasRole)) {
            throw new JwtInvalidException();
        }
        request.setAttribute(AUTH_PRINCIPAL, new AuthPrincipal(token.getSubject()));

        return true;
    }
}
