package com.room18.stock.controller;

import com.room18.common.R;
import com.room18.common.VO.StockVO;
import com.room18.common.entity.StockDetail;
import com.room18.stock.service.StockDetailService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "StockDetail data interface")
@RestController
@CrossOrigin(origins = "*")
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
    
    @GetMapping("/findStockDetailByStockId/{stockId}")
    public R findStockDetailByStockId(@PathVariable Long stockId){
        StockDetail stockDetail = stockDetailService.findStockDetailByStockId(stockId);
        if(stockDetail != null){
            return R.ok().put("data", stockDetail);
        }
        else {
            return R.error(404, "There is no stockDetail with the stockId.");
        }

    }

    // Other controller methods
}
