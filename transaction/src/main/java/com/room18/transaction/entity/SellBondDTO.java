package com.room18.transaction.entity;

import lombok.Data;

@Data
public class SellStockDTO {
    private Long stockId;
    private Long buyAmount;
    private Double sellPrice;
}
