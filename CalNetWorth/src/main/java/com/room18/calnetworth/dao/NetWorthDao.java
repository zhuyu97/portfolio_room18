package com.room18.calnetworth.dao;

import com.room18.common.entity.HoldAssets;
import com.room18.common.entity.NetWorth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetWorthDao extends JpaRepository<NetWorth, Long> {
    // Custom query methods or additional operations can be defined here
    @Query(value = "SELECT * FROM net_worth ORDER BY `time` DESC LIMIT 1",nativeQuery = true)
    public NetWorth getCurrentNetWorth();

    @Query(value = "SELECT * FROM net_worth ORDER BY `time`",nativeQuery = true)
    public List<NetWorth> getAllNetWorthsOrderByTime();

}