package com.room18.common.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Net_Worth")
public class NetWorth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "net_worth_id")
    private Long netWorthId;

    @Column(name = "net_worth_value")
    private BigDecimal netWorthValue;

    @Column(name = "time")
    private LocalDateTime time;
}