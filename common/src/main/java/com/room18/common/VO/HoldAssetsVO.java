package com.room18.common.VO;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
public class HoldAssetsVO {
    private Long holdAssetsId;

    private Long userId;

    private Long productionId;

    private String productionTypeName;

    private Long productionAmount;

    private BigDecimal totalValue;
}
