package com.room18.common.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Bond_detail")
public class BondDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bond_detail_id")
    private Long bondDetailId;

    @Column(name = "bond_id")
    private Long bondId;

    @Column(name = "bond_price")
    private BigDecimal bondPrice;

    @Column(name = "time")
    private LocalDateTime time;

    // Getter和Setter方法
}