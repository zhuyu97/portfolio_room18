package com.room18.bond.dao;

import com.room18.common.entity.BondDetail;
import com.room18.common.entity.StockDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BondDetailDao extends JpaRepository<BondDetail, Long> {
    // 自定义查询方法
    @Query(value = "SELECT * FROM bond_detail WHERE bond_id = :bondId ORDER BY `time` DESC LIMIT 1",nativeQuery = true)
    public BondDetail findBondDetailByBondId(@Param("bondId") Long bondId);
}