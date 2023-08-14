package com.room18.common.VO;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StockVO {
    private Long stockId;

    private String stockName;

    private BigDecimal stockPrice;

    private LocalDateTime time;
}
