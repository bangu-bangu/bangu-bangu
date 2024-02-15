package com.github.bbooong.bangumall.order.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderLineRequest(
        @NotNull @NotBlank @Min(1) long productId, @NotNull @NotBlank @Min(1) int quantity) {}
