package com.room18.individualaccount.service;

import com.room18.individualaccount.dao.CashDao;
import com.room18.common.entity.Cash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashService {
    @Autowired
    private CashDao cashDao;

    public List<Cash> getAllCash() {
        return cashDao.findAll();
    }

    public Cash getCashById(Long cashId) {
        return cashDao.findById(cashId).orElse(null);
    }

    public Cash saveCash(Cash cash) {
        return cashDao.save(cash);
    }

    public void deleteCash(Long cashId) {
        cashDao.deleteById(cashId);
    }

    // Other service methods
}
