package com.github.bbooong.bangumall.stock.application.dto;

import jakarta.validation.constraints.Positive;

public record StockDecreaseRequest(@Positive long productId, @Positive int quantity) {}
