package com.github.bbooong.bangumall.product.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductCreateRequest(
        @NotBlank @Size(max = 255) String name,
        @Positive int price,
        @NotBlank String description) {}
