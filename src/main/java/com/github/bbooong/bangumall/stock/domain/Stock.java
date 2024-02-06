package com.github.bbooong.bangumall.stock.domain;

import com.github.bbooong.bangumall.stock.exception.StockQuantityNotEnoughException;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stocks")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private LocalDate expiredDate;

    public static Stock create(
            final long productId, final int quantity, final LocalDate expiredDate) {
        return new Stock(null, productId, quantity, expiredDate);
    }

    public void update(final int quantity, final LocalDate expiredDate) {
        this.quantity = quantity;
        this.expiredDate = expiredDate;
    }

    public void decreaseQuantity(final int quantity) {
        if (this.quantity < quantity) {
            throw new StockQuantityNotEnoughException(); // TODO: 도메인 별 예외 처리
        }

        this.quantity -= quantity;
    }
}
