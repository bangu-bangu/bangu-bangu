package com.github.bbooong.bangumall.stock.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    @AttributeOverride(name = "value", column = @Column(name = "quantity"))
    private Quantity quantity;

    @Column(nullable = false)
    private LocalDate expiredDate;

    public static Stock create(
            final long productId, final int quantity, final LocalDate expiredDate) {
        return new Stock(null, productId, Quantity.create(quantity), expiredDate);
    }

    public void update(final int quantity, final LocalDate expiredDate) {
        this.quantity = Quantity.create(quantity);
        this.expiredDate = expiredDate;
    }

    public void decreaseQuantity(final int quantity) {
        this.quantity = this.quantity.subtract(quantity);
    }
}
