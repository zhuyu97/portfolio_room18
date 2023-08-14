package com.room18.transaction.entity;

import lombok.Data;

@Data
public class SellStockDTO {
    private Long stockId;
    private Long sellAmount;
    private Double sellPrice;
}
