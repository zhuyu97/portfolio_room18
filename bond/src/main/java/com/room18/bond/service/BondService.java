package com.room18.bond.service;

import com.room18.bond.dao.BondDao;
import com.room18.common.entity.Bond;
import com.room18.common.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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

    public List<Bond> findBondByFuzzyQuery(String queryString) {
        List<Bond> bonds = bondDao.findBondByFuzzyQuery(queryString);
        return bonds;
    }

    // 其他服务方法
}