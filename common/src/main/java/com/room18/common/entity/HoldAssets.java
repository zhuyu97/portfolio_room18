package com.room18.common.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Hold_assets")
public class HoldAssets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hold_assets_id")
    private Long holdAssetsId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "production_id")
    private Long productionId;

    @Column(name = "production_type")
    private Integer productionType;

    @Column(name = "production_amount")
    private Long productionAmount;

    // Getter and Setter methods
}
