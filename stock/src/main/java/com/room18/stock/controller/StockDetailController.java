package com.room18.stock.controller;

import com.room18.common.R;
import com.room18.stock.entity.StockDetail;
import com.room18.stock.service.StockDetailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "StockDetail data interface")
@RestController
@RequestMapping("/api/stockDetails")
public class StockDetailController {
    @Autowired
    private StockDetailService stockDetailService;

    @GetMapping("/getAll")
    public R getAllStockDetails() {
        return R.ok().put("data", stockDetailService.getAllStockDetails());
    }

    @GetMapping("/{stockDetailId}")
    public R getStockDetailById(@PathVariable Long stockDetailId) {
        return R.ok().put("data", stockDetailService.getStockDetailById(stockDetailId));
    }

    @PostMapping("/")
    public R createStockDetail(@RequestBody StockDetail stockDetail) {
        return R.ok().put("data", stockDetailService.saveStockDetail(stockDetail));
    }

    @PutMapping("/{stockDetailId}")
    public R updateStockDetail(@PathVariable Long stockDetailId, @RequestBody StockDetail stockDetail) {
        StockDetail existingStockDetail = stockDetailService.getStockDetailById(stockDetailId);
        if (existingStockDetail != null) {
            // Update the existing stock detail
            existingStockDetail.setStockId(stockDetail.getStockId());
            existingStockDetail.setStockPrice(stockDetail.getStockPrice());
            existingStockDetail.setTime(stockDetail.getTime());
            return R.ok().put("data", stockDetailService.saveStockDetail(existingStockDetail));
        }
        return R.error("The stockDetail id doesn't exist");
    }

    @DeleteMapping("/{stockDetailId}")
    public R deleteStockDetail(@PathVariable Long stockDetailId) {
        stockDetailService.deleteStockDetail(stockDetailId);
        return R.ok().put("message", "Successfully deleted");
    }

    // Other controller methods
}
