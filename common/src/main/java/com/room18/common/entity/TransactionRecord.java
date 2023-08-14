package com.room18.common.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "Transaction_record")
public class TransactionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tr_id")
    private Long trId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "transaction_type")
    private Integer transactionType;

    @Column(name = "number_of_transaction")
    private Long numberOfTransaction;

    @Column(name = "production_id")
    private Long productionId;

    @Column(name = "production_type")
    private Integer productionType;

    @Column(name = "production_detail_id")
    private Long productionDetailId;

    @Column(name = "production_price")
    private BigDecimal productionPrice;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "remain_cash")
    private BigDecimal remainCash;

    @Column(name = "time")
    private LocalDateTime time;

    // Getter and Setter methods
}