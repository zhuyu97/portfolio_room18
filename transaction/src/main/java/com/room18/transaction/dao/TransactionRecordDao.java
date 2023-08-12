package com.room18.transaction.dao;

import com.room18.common.entity.TransactionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRecordDao extends JpaRepository<TransactionRecord, Long> {
    // Custom query methods
}