package com.github.bbooong.bangumall.order.domain;

import com.github.bbooong.bangumall.core.entity.BaseEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @CollectionTable(name = "order_lines", joinColumns = @JoinColumn(name = "order_id"))
    @ElementCollection(fetch = FetchType.LAZY)
    @OrderColumn(name = "line_idx")
    private List<OrderLine> orderLines;

    public static Order create(final long memberId, final List<OrderLine> orderLines) {
        return new Order(null, memberId, orderLines);
    }
}
