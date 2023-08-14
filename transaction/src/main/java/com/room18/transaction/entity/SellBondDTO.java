package com.room18.transaction.entity;

import lombok.Data;

@Data
public class SellBondDTO {
    private Long bondId;
    private Long sellAmount;
    private Double sellPrice;
}
