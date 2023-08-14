package com.room18.stock.dao;

import com.room18.common.entity.Stock;
import com.room18.common.entity.StockDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockDao extends JpaRepository<Stock, Long> {
    // Custom query methods

    @Query(value = "SELECT * FROM stocks WHERE stock_id LIKE %:queryString% or stock_name LIKE %:queryString%",nativeQuery = true)
    public List<Stock> findStockByFuzzyQuery(@Param("queryString") String queryString);
}