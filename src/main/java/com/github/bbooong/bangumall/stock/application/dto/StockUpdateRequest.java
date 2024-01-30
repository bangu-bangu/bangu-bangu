package com.github.bbooong.bangumall.stock.application.dto;

import java.time.LocalDate;

public record StockUpdateRequest(int quantity, LocalDate expiredDate) {}
