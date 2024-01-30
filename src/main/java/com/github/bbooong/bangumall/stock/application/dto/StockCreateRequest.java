package com.github.bbooong.bangumall.stock.application.dto;

import java.time.LocalDate;

public record StockCreateRequest(int quantity, LocalDate expiredDate) {}
