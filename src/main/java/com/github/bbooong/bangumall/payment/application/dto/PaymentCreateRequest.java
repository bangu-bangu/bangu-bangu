package com.github.bbooong.bangumall.payment.application.dto;

import jakarta.validation.constraints.Positive;

public record PaymentCreateRequest(@Positive long orderId) {}
