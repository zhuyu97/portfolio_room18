package com.room18.stock.controller;

import com.room18.common.R;
import com.room18.common.entity.Stock;
import com.room18.stock.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Stock data interface")
@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    private StockService stockService;

    @ApiOperation(value ="Get all stocks")
    @GetMapping("/getAll")
    public R getAllStocks() {
        return R.ok().put("data", stockService.getAllStocks());
    }

    @ApiOperation(value ="Get stock stocks")
    @GetMapping("/{stockId}")
    public R getStockById(@PathVariable Long stockId) {
        return R.ok().put("data", stockService.getStocksById(stockId));
    }

    @GetMapping("/fuzzyQuery/{queryString}")
    public R getStockByFuzzyQuery(){
        //Todo
        return R.ok();
    }

    @PostMapping("/")
    public R createStock(@RequestBody Stock stock) {
        return R.ok().put("data", stockService.saveStocks(stock));
    }

    @PutMapping("/{stockId}")
    public R updateStock(@PathVariable Long stockId, @RequestBody Stock stock) {
        Stock existingStocks = stockService.getStocksById(stockId);
        if (existingStocks != null) {
            // Update the existing stocks
            existingStocks.setStockName(stock.getStockName());
            return R.ok().put("data", stockService.saveStocks(existingStocks));
        }
        return R.error("The stock id doesn't exist");
    }

    @DeleteMapping("/{stockId}")
    public R deleteStock(@PathVariable Long stockId) {
        stockService.deleteStocks(stockId);
        return R.ok().put("message", "Successfully deleted");
    }

    // Other controller methods
}