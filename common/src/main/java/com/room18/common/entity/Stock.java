package com.room18.common.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "stock_name")
    private String stockName;

    // Getters and Setters
}