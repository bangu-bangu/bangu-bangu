package com.github.bbooong.bangumall.core;

import jakarta.validation.constraints.NotBlank;

public record TestValidationRequest(@NotBlank String test) {}
