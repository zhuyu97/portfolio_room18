package com.room18.stock.service;

import com.room18.common.VO.StockVO;
import com.room18.stock.dao.StockDetailDao;
import com.room18.common.entity.StockDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StockDetailService {
    @Autowired
    private StockDetailDao stockDetailDao;

    public List<StockDetail> getAllStockDetails() {
        return stockDetailDao.findAll();
    }

    public StockDetail getStockDetailById(Long stockDetailId) {
        return stockDetailDao.findById(stockDetailId).orElse(null);
    }

    public StockDetail saveStockDetail(StockDetail stockDetail) {
        return stockDetailDao.save(stockDetail);
    }

    public void deleteStockDetail(Long stockDetailId) {
        stockDetailDao.deleteById(stockDetailId);
    }

    public StockDetail findStockDetailByStockId(Long stockId){
        return stockDetailDao.findStockDetailByStockId(stockId);
    }

    public List<StockDetail> getTodayPricesByStockId(Long stockId){
        return stockDetailDao.getTodayPricesByStockId(stockId);
    }


    // Other service methods
}