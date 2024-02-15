package com.github.bbooong.bangumall.order.application.dto;

import jakarta.validation.constraints.Size;
import java.util.List;

public record OrderCreateRequest(@Size(min = 1) List<OrderLineRequest> orderLines) {}
