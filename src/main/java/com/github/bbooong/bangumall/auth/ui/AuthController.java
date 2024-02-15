package com.github.bbooong.bangumall.auth.ui;

import com.github.bbooong.bangumall.auth.application.AuthService;
import com.github.bbooong.bangumall.auth.application.dto.AuthLoginRequest;
import com.github.bbooong.bangumall.auth.application.dto.AuthLoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthLoginResponse login(@Valid @RequestBody final AuthLoginRequest request) {
        return authService.login(request);
    }
}
