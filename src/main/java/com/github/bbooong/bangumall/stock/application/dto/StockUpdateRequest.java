package com.github.bbooong.bangumall.stock.application.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public record StockUpdateRequest(@Positive int quantity, @Future LocalDate expiredDate) {}
