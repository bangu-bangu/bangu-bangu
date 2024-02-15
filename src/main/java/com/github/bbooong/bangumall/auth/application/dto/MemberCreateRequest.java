package com.github.bbooong.bangumall.auth.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MemberCreateRequest(
        @NotBlank @Email String email, @NotBlank @Size(min = 1, max = 255) String password) {}
