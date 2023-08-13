package com.room18.calnetworth.dao;

import com.room18.common.entity.NetWorth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetWorthDao extends JpaRepository<NetWorth, Long> {
    // Custom query methods or additional operations can be defined here
}