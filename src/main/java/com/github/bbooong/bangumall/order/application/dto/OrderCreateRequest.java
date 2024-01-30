package com.github.bbooong.bangumall.order.application.dto;

import java.util.List;

public record OrderCreateRequest(List<OrderLineRequest> orderLines) {}
