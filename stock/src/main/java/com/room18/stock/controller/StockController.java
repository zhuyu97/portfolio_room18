package com.room18.stock.controller;

import com.room18.stock.entity.Stock;
import com.room18.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping("/getAllStocks")
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }

    @GetMapping("/{stockId}")
    public Stock getStocksById(@PathVariable Long stockId) {
        return stockService.getStocksById(stockId);
    }

    @PostMapping("/")
    public Stock createStock(@RequestBody Stock stock) {
        return stockService.saveStocks(stock);
    }

    @PutMapping("/{stockId}")
    public Stock updateStocks(@PathVariable Long stockId, @RequestBody Stock stock) {
        Stock existingStocks = stockService.getStocksById(stockId);
        if (existingStocks != null) {
            // Update the existing stocks
            existingStocks.setStockName(stock.getStockName());
            return stockService.saveStocks(existingStocks);
        }
        return null;
    }

    @DeleteMapping("/{stockId}")
    public void deleteStocks(@PathVariable Long stockId) {
        stockService.deleteStocks(stockId);
    }

    // Other controller methods
}