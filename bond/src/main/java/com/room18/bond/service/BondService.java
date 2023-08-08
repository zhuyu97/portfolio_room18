package com.room18.bond.service;

import com.room18.bond.dao.BondDao;
import com.room18.bond.entity.Bond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BondService {
    @Autowired
    private BondDao bondDao;

    public List<Bond> getAllBonds() {
        return bondDao.findAll();
    }

    public Bond getBondById(Long bondId) {
        return bondDao.findById(bondId).orElse(null);
    }

    public Bond saveBond(Bond bond) {
        return bondDao.save(bond);
    }

    public void deleteBond(Long bondId) {
        bondDao.deleteById(bondId);
    }

    // 其他服务方法
}