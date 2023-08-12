package com.room18.individualaccount.dao;

import com.room18.common.entity.HoldAssets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoldAssetsDao extends JpaRepository<HoldAssets, Long> {
    // Custom query methods
}
