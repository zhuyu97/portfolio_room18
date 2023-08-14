package com.room18.individualaccount.dao;

import com.room18.common.entity.HoldAssets;
import com.room18.common.entity.Stock;
import com.room18.individualaccount.service.HoldAssetsService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HoldAssetsDao extends JpaRepository<HoldAssets, Long> {
    // Custom query methods

    @Query(value = "SELECT * FROM hold_assets WHERE production_id = :productionId AND production_type = :productionType",nativeQuery = true)
    public HoldAssets getHoldAssetsByProductionIdAndType(@Param("productionId") Long productionId, @Param("productionType") Integer productionType);

}
