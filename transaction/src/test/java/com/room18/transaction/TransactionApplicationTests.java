package com.room18.transaction;

import com.room18.common.R;
import com.room18.transaction.controller.TransactionRecordController;
import com.room18.transaction.entity.BuyStockDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionApplicationTests {
    @Autowired
    private TransactionRecordController transactionRecordController;

    @Test
    void contextLoads() {
        BuyStockDTO buyStockDTO = new BuyStockDTO();
        buyStockDTO.setStockId(1L);
        buyStockDTO.setBuyAmount(1000L);

        R r = transactionRecordController.buyStock(buyStockDTO);
    }

}
