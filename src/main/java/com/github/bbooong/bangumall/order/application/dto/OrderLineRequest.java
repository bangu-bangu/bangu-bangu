package com.github.bbooong.bangumall.order.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record OrderLineRequest(@NotBlank @Min(1) long productId, @NotBlank @Min(1) int quantity) {}
