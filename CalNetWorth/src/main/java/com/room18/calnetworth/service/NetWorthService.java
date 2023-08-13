package com.room18.calnetworth.service;

import com.room18.calnetworth.dao.NetWorthDao;
import com.room18.common.entity.NetWorth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetWorthService {
    @Autowired
    private NetWorthDao netWorthDao;


    public NetWorth saveNetWorth(NetWorth netWorth) {
        return netWorthDao.save(netWorth);
    }

    public NetWorth getNetWorthById(Long id) {
        return netWorthDao.findById(id).orElse(null);
    }

    public List<NetWorth> getAllNetWorths() {
        return netWorthDao.findAll();
    }

    public void deleteNetWorth(Long netWorthId) {
        netWorthDao.deleteById(netWorthId);
    }

    // Additional methods can be added based on your requirements
}
