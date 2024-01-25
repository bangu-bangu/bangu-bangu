package com.github.bbooong.bangumall.auth.ui;

import com.github.bbooong.bangumall.auth.application.AuthService;
import com.github.bbooong.bangumall.auth.application.dto.LoginRequest;
import com.github.bbooong.bangumall.auth.application.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody final LoginRequest request) {
        return authService.login(request);
    }
}
