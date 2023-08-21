package com.room18.stock.dao;

import com.room18.common.VO.StockVO;
import com.room18.common.entity.StockDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface StockDetailDao extends JpaRepository<StockDetail, Long> {
    // Custom query methods
    @Query(value = "SELECT * FROM stock_detail WHERE stock_id = :stockId ORDER BY `time` DESC LIMIT 1",nativeQuery = true)
    public StockDetail findStockDetailByStockId(@Param("stockId") Long stockId);

    @Query(value = "SELECT * FROM stock_detail WHERE stock_id = :stockId AND DATE(time) = CURDATE() ORDER BY `time` ASC",nativeQuery = true)
    public List<StockDetail> getTodayPricesByStockId(@Param("stockId") Long stockId);
}