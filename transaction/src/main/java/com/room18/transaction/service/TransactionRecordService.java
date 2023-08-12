package com.room18.transaction.service;

import com.room18.transaction.dao.TransactionRecordDao;
import com.room18.common.entity.TransactionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionRecordService {
    @Autowired
    private TransactionRecordDao transactionRecordDao;


    public List<TransactionRecord> getAllTransactionRecords() {
        return transactionRecordDao.findAll();
    }

    public TransactionRecord getTransactionRecordById(Long trId) {
        return transactionRecordDao.findById(trId).orElse(null);
    }

    public TransactionRecord saveTransactionRecord(TransactionRecord transactionRecord) {
        return transactionRecordDao.save(transactionRecord);
    }

    public void deleteTransactionRecord(Long trId) {
        transactionRecordDao.deleteById(trId);
    }

    // Other service methods
}

