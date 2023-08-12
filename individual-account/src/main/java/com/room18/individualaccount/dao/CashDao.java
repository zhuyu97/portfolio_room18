package com.room18.individualaccount.dao;

import com.room18.common.entity.Cash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashDao extends JpaRepository<Cash, Long> {
    // Custom query methods
}