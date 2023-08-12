package com.room18.stock.dao;

import com.room18.common.entity.StockDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDetailDao extends JpaRepository<StockDetail, Long> {
    // Custom query methods
}