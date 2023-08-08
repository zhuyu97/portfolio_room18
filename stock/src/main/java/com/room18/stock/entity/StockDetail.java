package com.room18.stock.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Stock_detail")
public class StockDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_detail_id")
    private Long stockDetailId;

    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "stock_price")
    private BigDecimal stockPrice;

    @Column(name = "time")
    private LocalDateTime time;

    // Getters and Setters
}