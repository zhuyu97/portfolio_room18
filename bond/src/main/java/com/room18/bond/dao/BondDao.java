package com.room18.bond.dao;

import com.room18.common.entity.Bond;
import com.room18.common.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BondDao extends JpaRepository<Bond, Long> {
    // 自定义查询方法
    @Query(value = "SELECT * FROM bonds WHERE bond_id LIKE %:queryString% or bond_name LIKE %:queryString%",nativeQuery = true)
    public List<Bond> findBondByFuzzyQuery(@Param("queryString") String queryString);
}
