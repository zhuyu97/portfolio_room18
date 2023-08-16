package com.room18.stock.controller;

import com.room18.common.R;
import com.room18.common.VO.StockVO;
import com.room18.common.entity.Stock;
import com.room18.common.entity.StockDetail;
import com.room18.stock.service.StockDetailService;
import com.room18.stock.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "Stock data interface")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    private StockService stockService;

    @Autowired
    private StockDetailService stockDetailService;

    @ApiOperation(value ="Get all stocks")
    @GetMapping("/getAll")
    public R getAllStocks() {
        List<Stock> stockList = stockService.getAllStocks();
        List<StockVO> stockVOList = new ArrayList<>();
        if(stockList.size() > 0){
            for(Stock stock: stockList){
                StockDetail stockDetail = stockDetailService.findStockDetailByStockId(stock.getStockId());
                StockVO stockVO = new StockVO();
                BeanUtils.copyProperties(stockDetail, stockVO);
                stockVO.setStockName(stock.getStockName());
                stockVOList.add(stockVO);
            }
            return R.ok().put("data", stockVOList);
        }
        return R.error(404, "No stocks found.");
    }

    @ApiOperation(value ="Get stock stocks")
    @GetMapping("/{stockId}")
    public R getStockById(@PathVariable Long stockId) {
        Stock stock = stockService.getStocksById(stockId);
        if(stock != null){
            StockDetail stockDetail = stockDetailService.findStockDetailByStockId(stock.getStockId());
            StockVO stockVO = new StockVO();
            BeanUtils.copyProperties(stockDetail, stockVO);
            stockVO.setStockName(stock.getStockName());
            return R.ok().put("data", stockVO);
        }
        else {
            return R.error(404, "There is no stock with the stockId.");
        }
    }

    @GetMapping("/fuzzyQuery/{queryString}")
    public R getStockByFuzzyQuery(@PathVariable("queryString") String queryString){
        List<Stock> stockList = stockService.findStockByFuzzyQuery(queryString);
        List<StockVO> stockVOList = new ArrayList<>();
        if(stockList.size() > 0){
            for(Stock stock: stockList){
                StockDetail stockDetail= stockDetailService.findStockDetailByStockId(stock.getStockId());
                StockVO stockVO = new StockVO();
                BeanUtils.copyProperties(stockDetail, stockVO);
                stockVO.setStockName(stock.getStockName());
                stockVOList.add(stockVO);
            }
            return R.ok().put("data", stockVOList);
        }
        return R.error(404, "No stocks found.");
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