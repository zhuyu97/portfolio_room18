package com.room18.stock.controller;

import com.room18.common.R;
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
    public R getAllStocks() {
        return R.ok().put("data", stockService.getAllStocks());
    }

    @GetMapping("/{stockId}")
    public R getStocksById(@PathVariable Long stockId) {
        return R.ok().put("data", stockService.getStocksById(stockId));
    }

    @PostMapping("/")
    public R createStock(@RequestBody Stock stock) {
        return R.ok().put("data", stockService.saveStocks(stock));
    }

    @PutMapping("/{stockId}")
    public R updateStocks(@PathVariable Long stockId, @RequestBody Stock stock) {
        Stock existingStocks = stockService.getStocksById(stockId);
        if (existingStocks != null) {
            // Update the existing stocks
            existingStocks.setStockName(stock.getStockName());
            return R.ok().put("data", stockService.saveStocks(existingStocks));
        }
        return R.error("The stock id doesn't exist");
    }

    @DeleteMapping("/{stockId}")
    public R deleteStocks(@PathVariable Long stockId) {
        stockService.deleteStocks(stockId);
        return R.ok().put("message", "Successfully deleted");
    }

    // Other controller methods
}