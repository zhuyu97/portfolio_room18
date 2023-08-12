package com.room18.transaction.controller;

import com.room18.common.R;
import com.room18.common.entity.TransactionRecord;
import com.room18.transaction.service.StockFeignService;
import com.room18.transaction.service.TransactionRecordService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "TransactionRecord data interface")
@RestController
@RequestMapping("/api/transactionRecord")
public class TransactionRecordController {
    @Autowired
    private TransactionRecordService transactionRecordService;

    @Autowired
    private StockFeignService stockFeignService;

    @GetMapping("/getAll")
    public R getAllTransactionRecords() {
        return R.ok().put("data", transactionRecordService.getAllTransactionRecords());
    }

    @GetMapping("/{trId}")
    public R getTransactionRecordById(@PathVariable Long trId) {
        return R.ok().put("data", transactionRecordService.getTransactionRecordById(trId));
    }

    @PostMapping("/")
    public R createTransactionRecord(@RequestBody TransactionRecord transactionRecord) {
        return R.ok().put("data", transactionRecordService.saveTransactionRecord(transactionRecord));
    }

    @PutMapping("/{trId}")
    public R updateTransactionRecord(@PathVariable Long trId, @RequestBody TransactionRecord transactionRecord) {
        TransactionRecord existingRecord = transactionRecordService.getTransactionRecordById(trId);
        if (existingRecord != null) {
            // Update existing transaction record
            BeanUtils.copyProperties(transactionRecord, existingRecord);
            // Update other fields as needed
            return R.ok().put("data", transactionRecordService.saveTransactionRecord(existingRecord));
        }
        return R.error("The transactionRecord id doesn't exist");
    }

    @DeleteMapping("/{trId}")
    public R deleteTransactionRecord(@PathVariable Long trId) {
        transactionRecordService.deleteTransactionRecord(trId);
        return R.ok().put("message", "Successfully deleted");
    }

    @GetMapping("/testOpenfeign/{stockId}")
    public R getStockByIdByOpenfeign(@PathVariable Long stockId) {
        R r = stockFeignService.getStockById(stockId);
        return r;
    }

    // Other controller methods
}
