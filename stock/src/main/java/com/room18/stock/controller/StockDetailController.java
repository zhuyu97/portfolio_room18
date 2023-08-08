package com.room18.stock.controller;

import com.room18.stock.entity.StockDetail;
import com.room18.stock.service.StockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stockDetails")
public class StockDetailController {
    @Autowired
    private StockDetailService stockDetailService;


    public StockDetailController(StockDetailService stockDetailService) {
        this.stockDetailService = stockDetailService;
    }

    @GetMapping("/getAll")
    public List<StockDetail> getAllStockDetails() {
        return stockDetailService.getAllStockDetails();
    }

    @GetMapping("/{stockDetailId}")
    public StockDetail getStockDetailById(@PathVariable Long stockDetailId) {
        return stockDetailService.getStockDetailById(stockDetailId);
    }

    @PostMapping("/")
    public StockDetail createStockDetail(@RequestBody StockDetail stockDetail) {
        return stockDetailService.saveStockDetail(stockDetail);
    }

    @PutMapping("/{stockDetailId}")
    public StockDetail updateStockDetail(@PathVariable Long stockDetailId, @RequestBody StockDetail stockDetail) {
        StockDetail existingStockDetail = stockDetailService.getStockDetailById(stockDetailId);
        if (existingStockDetail != null) {
            // Update the existing stock detail
            existingStockDetail.setStockId(stockDetail.getStockId());
            existingStockDetail.setStockPrice(stockDetail.getStockPrice());
            existingStockDetail.setTime(stockDetail.getTime());
            return stockDetailService.saveStockDetail(existingStockDetail);
        }
        return null;
    }

    @DeleteMapping("/{stockDetailId}")
    public void deleteStockDetail(@PathVariable Long stockDetailId) {
        stockDetailService.deleteStockDetail(stockDetailId);
    }

    // Other controller methods
}
