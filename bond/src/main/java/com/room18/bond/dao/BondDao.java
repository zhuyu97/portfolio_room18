package com.room18.bond.dao;

import com.room18.bond.entity.Bond;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BondDao extends JpaRepository<Bond, Long> {
    // 自定义查询方法
}
