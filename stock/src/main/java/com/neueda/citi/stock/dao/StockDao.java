package com.neueda.citi.stock.dao;

import com.neueda.citi.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDao extends JpaRepository<Stock, Long> {
    // Custom query methods
}