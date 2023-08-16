package com.room18.insertdata;

import com.alibaba.fastjson.JSON;
import com.room18.common.R;
import com.room18.common.VO.BondVO;
import com.room18.common.VO.StockVO;
import com.room18.common.entity.BondDetail;
import com.room18.common.entity.StockDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EntityScan(basePackages = "com.room18.common.entity")
public class InsertDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(InsertDataApplication.class, args);
        ApplicationContext context = SpringUtil.getApplicationContext();

        ExecutorService executorService = Executors.newFixedThreadPool(2); // 设置线程池大小为5
        Random random = new Random();
        for (int i = 0; i < 1; i++) {
            if (i == 0){
                executorService.execute(() -> {
                    StockFeignService stockFeignService = context.getBean(StockFeignService.class);
                    R allStocks = stockFeignService.getAllStocks();
                    List<StockVO> stockVOList = JSON.parseArray(JSON.toJSONString(allStocks.get("data")), StockVO.class);

                    for (StockVO stockVO: stockVOList) {
                        insertStockToDatabase(stockVO, stockFeignService, random);
                    }
                    try {
                        Thread.sleep(1000); // 每秒钟插入一次
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    private static void insertStockToDatabase(StockVO stockVO, StockFeignService stockFeignService, Random random) {
        BigDecimal diff = BigDecimal.valueOf(random.nextDouble() * 0.04 - 0.02);
        BigDecimal stockPrice = stockVO.getStockPrice().add(diff);
        LocalDateTime time = LocalDateTime.now().minusSeconds(1);
        StockDetail stockDetail = new StockDetail();
        BeanUtils.copyProperties(stockVO, stockDetail);
        stockFeignService.createStockDetail(stockDetail);
        System.out.println("插入记录： stockId=" + stockVO.getStockId() + ", stockPrice=" + stockPrice + ", time=" + time);
    }
}
