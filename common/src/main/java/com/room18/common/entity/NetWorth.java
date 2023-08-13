package com.room18.common.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Net_Worth")
public class NetWorth {
    @Id
    @Column(name = "net_worth_id")
    private Long netWorthId;

    @Column(name = "net_worth_value")
    private Double netWorthValue;

    @Column(name = "time")
    private LocalDateTime time;
}