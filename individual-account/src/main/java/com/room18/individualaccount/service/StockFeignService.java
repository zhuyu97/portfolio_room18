package com.room18.individualaccount.service;

import com.room18.common.R;
import com.room18.common.entity.Stock;
import com.room18.common.entity.StockDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "stock")
public interface StockFeignService {
    @GetMapping("/api/stocks/getAll")
    public R getAllStocks();

    @GetMapping("/api/stocks/{stockId}")
    public R getStockById(@PathVariable Long stockId);

    @PostMapping("/api/stocks/")
    public R createStock(@RequestBody Stock stock);

    @PutMapping("/api/stocks/{stockId}")
    public R updateStock(@PathVariable Long stockId, @RequestBody Stock stock);

    @DeleteMapping("/api/stocks/{stockId}")
    public R deleteStock(@PathVariable Long stockId);

    @GetMapping("/api/stockDetails/getAll")
    public R getAllStockDetails();

    @GetMapping("/api/stockDetails/{stockDetailId}")
    public R getStockDetailById(@PathVariable Long stockDetailId);

    @PostMapping("/api/stockDetails/")
    public R createStockDetail(@RequestBody StockDetail stockDetail);

    @PutMapping("/api/stockDetails/{stockDetailId}")
    public R updateStockDetail(@PathVariable Long stockDetailId, @RequestBody StockDetail stockDetail);

    @DeleteMapping("/api/stockDetails/{stockDetailId}")
    public R deleteStockDetail(@PathVariable Long stockDetailId);

    @GetMapping("/api/stockDetails/findStockDetailByStockId/{stockId}")
    public R findStockDetailByStockId(@PathVariable Long stockId);
}
