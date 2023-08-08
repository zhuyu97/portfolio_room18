package com.room18.bond.dao;

import com.room18.bond.entity.BondDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BondDetailDao extends JpaRepository<BondDetail, Long> {
    // 自定义查询方法
}