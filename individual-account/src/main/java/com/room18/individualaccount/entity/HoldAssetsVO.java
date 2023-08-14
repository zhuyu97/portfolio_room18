package com.room18.individualaccount.entity;

import lombok.Data;

import javax.persistence.Column;

@Data
public class HoldAssetsVO {
    private Long holdAssetsId;

    private Long userId;

    private Long productionId;

    private String productionTypeName;

    private Long productionAmount;

    private Double totalValue;
}
