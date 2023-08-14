package com.room18.common.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Bonds")
public class Bond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bond_id")
    private Long bondId;

    @Column(name = "bond_name")
    private String bondName;

    // Getter和Setter方法
}