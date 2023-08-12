package com.room18.stock.dao;

import com.room18.common.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDao extends JpaRepository<Stock, Long> {
    // Custom query methods
}