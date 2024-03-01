package com.github.bbooong.bangumall.payment.application.dto;

import java.time.LocalDateTime;

public record PaymentInfoResponse(long totalPrice, LocalDateTime createdAt) {}
