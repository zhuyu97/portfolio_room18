package com.room18.transaction.controller;

import com.room18.transaction.entity.TransactionRecord;
import com.room18.transaction.service.TransactionRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactionRecord")
public class TransactionRecordController {
    @Autowired
    private TransactionRecordService transactionRecordService;

    @GetMapping("/getAll")
    public List<TransactionRecord> getAllTransactionRecords() {
        return transactionRecordService.getAllTransactionRecords();
    }

    @GetMapping("/{trId}")
    public TransactionRecord getTransactionRecordById(@PathVariable Long trId) {
        return transactionRecordService.getTransactionRecordById(trId);
    }

    @PostMapping("/")
    public TransactionRecord createTransactionRecord(@RequestBody TransactionRecord transactionRecord) {
        return transactionRecordService.saveTransactionRecord(transactionRecord);
    }

    @PutMapping("/{trId}")
    public TransactionRecord updateTransactionRecord(@PathVariable Long trId, @RequestBody TransactionRecord transactionRecord) {
        TransactionRecord existingRecord = transactionRecordService.getTransactionRecordById(trId);
        if (existingRecord != null) {
            // Update existing transaction record
            BeanUtils.copyProperties(transactionRecord, existingRecord);
            // Update other fields as needed
            return transactionRecordService.saveTransactionRecord(existingRecord);
        }
        return null;
    }

    @DeleteMapping("/{trId}")
    public void deleteTransactionRecord(@PathVariable Long trId) {
        transactionRecordService.deleteTransactionRecord(trId);
    }

    // Other controller methods
}
