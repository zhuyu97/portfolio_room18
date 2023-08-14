package com.room18.transaction.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionRecordVO {
    private Long trId;

    private Long userId;

    private String transactionTypeSellOrBuy;

    private Integer numberOfTransaction;

    private Long productionId;

    private String productionTypeName;

    private String productionName;

    private BigDecimal productionPrice;

    private BigDecimal cost;

    private BigDecimal remainCash;

    private LocalDateTime time;
}
