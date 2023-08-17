package com.room18.calnetworth.service;

import com.alibaba.fastjson.JSON;
import com.room18.calnetworth.dao.NetWorthDao;
import com.room18.common.R;
import com.room18.common.VO.HoldAssetsVO;
import com.room18.common.VO.StockVO;
import com.room18.common.entity.NetWorth;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NetWorthService {
    @Autowired
    private NetWorthDao netWorthDao;

    @Autowired
    private IndividualAccountFeignService individualAccountFeignService;


    public NetWorth saveNetWorth(NetWorth netWorth) {
        return netWorthDao.save(netWorth);
    }

    public NetWorth getNetWorthById(Long id) {
        return netWorthDao.findById(id).orElse(null);
    }

    public List<NetWorth> getAllNetWorthsOrderByTime() {
        return netWorthDao.getAllNetWorthsOrderByTime();
    }

    public void deleteNetWorth(Long netWorthId) {
        netWorthDao.deleteById(netWorthId);
    }

    public NetWorth getCurrentNetWorth(){
        return calCurrentNetWorth();
    }

    public NetWorth calCurrentNetWorth(){
        R allAssetsValueR = individualAccountFeignService.getAllAssetsValue();
        List<BigDecimal> allAssetsValueList = JSON.parseArray(JSON.toJSONString(allAssetsValueR.get("data")), BigDecimal.class);
        NetWorth netWorth = new NetWorth();
        BigDecimal totalValue = new BigDecimal(0);
        for(BigDecimal value: allAssetsValueList){
            totalValue = totalValue.add(value);
        }


        netWorth.setNetWorthValue(totalValue);
        netWorth.setTime(LocalDateTime.now());

        netWorthDao.save(netWorth);
        return netWorth;
    }

    // Additional methods can be added based on your requirements
}
