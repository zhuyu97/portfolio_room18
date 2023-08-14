package com.room18.transaction.entity;

import lombok.Data;

@Data
public class BuyStockDTO {
    private Long stockId;
    private Long buyAmount;
}
