package com.room18.insertdata;

import com.alibaba.fastjson.JSON;
import com.room18.common.R;
import com.room18.common.VO.StockVO;
import com.room18.common.entity.NetWorth;
import com.room18.common.entity.StockDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
public class StockDataInsertTask {
    @Autowired
    private StockFeignService stockFeignService;

    @Autowired
    private NetWorthFeignService netWorthFeignService;

    private Random random;

    public StockDataInsertTask() {
        this.random = new Random();
    }

    @Scheduled(fixedRate = 1000) // 每秒钟执行一次任务
    public void insertStockData() {
        R allStocks = stockFeignService.getAllStocks();
        List<StockVO> stockVOList = JSON.parseArray(JSON.toJSONString(allStocks.get("data")), StockVO.class);

        for (StockVO stockVO : stockVOList) {
            insertStockToDatabase(stockVO);
        }
    }

    @Scheduled(fixedRate = 1000) // 每秒钟执行一次任务
    public void calNetWorthEachSecond() {
        R netWorthR = netWorthFeignService.calCurrentNetWorth();
    }

    private void insertStockToDatabase(StockVO stockVO) {
        BigDecimal diff = BigDecimal.valueOf(random.nextDouble() * 0.1 - 0.05);
        BigDecimal stockPrice = stockVO.getStockPrice().add(diff);
        LocalDateTime time = LocalDateTime.now().minusSeconds(1);
        StockDetail stockDetail = new StockDetail();
        BeanUtils.copyProperties(stockVO, stockDetail);
        stockDetail.setStockPrice(stockPrice);
        stockDetail.setTime(time);
        stockFeignService.createStockDetail(stockDetail);
        System.out.println("插入记录： stockId=" + stockVO.getStockId() + ", stockPrice=" + stockPrice + ", time=" + time);
    }
}
