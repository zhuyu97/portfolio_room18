package com.room18.common.VO;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BondVO {
    private Long bondId;

    private String bondName;

    private BigDecimal bondPrice;

    private LocalDateTime time;
}
