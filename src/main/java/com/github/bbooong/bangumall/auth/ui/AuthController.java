package com.github.bbooong.bangumall.auth.ui;

import com.github.bbooong.bangumall.auth.dto.LoginResponse;
import com.github.bbooong.bangumall.member.dto.MemberCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody MemberCreateRequest request) {
        return new LoginResponse("");
    }
}
