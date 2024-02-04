package com.github.bbooong.bangumall.order.application;

import com.github.bbooong.bangumall.order.application.dto.OrderCreateRequest;
import com.github.bbooong.bangumall.order.application.dto.OrderLineRequest;
import com.github.bbooong.bangumall.order.domain.Order;
import com.github.bbooong.bangumall.order.domain.OrderLine;
import com.github.bbooong.bangumall.order.domain.OrderRepository;
import com.github.bbooong.bangumall.order.domain.ProductClient;
import com.github.bbooong.bangumall.order.domain.ProductInfo;
import com.github.bbooong.bangumall.order.domain.StockClient;
import com.github.bbooong.bangumall.stock.application.dto.StockDecreaseRequest;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductClient productClient;
    private final StockClient stockClient;
    private final OrderRepository orderRepository;

    @Transactional
    public long createOrder(final long memberId, final OrderCreateRequest request) {
        final List<OrderLine> orderLines = new ArrayList<>();
        final List<StockDecreaseRequest> stockDecreaseRequests = new ArrayList<>();
        for (final OrderLineRequest entry : request.orderLines()) {
            final ProductInfo productInfo = productClient.getProduct(entry.productId());
            orderLines.add(
                    OrderLine.create(productInfo.id(), productInfo.price(), entry.quantity()));
            stockDecreaseRequests.add(
                    new StockDecreaseRequest(entry.productId(), entry.quantity()));
        }
        stockClient.decreaseStocks(stockDecreaseRequests);

        return orderRepository.save(Order.create(memberId, orderLines)).getId();
    }
}
