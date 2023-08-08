package com.room18.stock.service;

import com.room18.stock.dao.StockDetailDao;
import com.room18.stock.entity.StockDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockDetailService {
    @Autowired
    private StockDetailDao stockDetailDao;

    public StockDetailService(StockDetailDao stockDetailDao) {
        this.stockDetailDao = stockDetailDao;
    }

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

    // Other service methods
}