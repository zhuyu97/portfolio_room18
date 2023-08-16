package com.room18.common.VO;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class HoldAssetsVO {
    private Long holdAssetsId;

    private Long userId;

    private Long productionId;

    private String productionTypeName;

    private Long productionAmount;

    private BigDecimal holdingCost;

    private BigDecimal income;

    private double incomeRate;
}
