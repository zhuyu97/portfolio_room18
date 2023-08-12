package com.room18.individualaccount.service;

import com.room18.individualaccount.dao.HoldAssetsDao;
import com.room18.common.entity.HoldAssets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoldAssetsService {
    @Autowired
    private HoldAssetsDao holdAssetsDao;

    public List<HoldAssets> getAllHoldAssets() {
        return holdAssetsDao.findAll();
    }

    public HoldAssets getHoldAssetsById(Long holdAssetsId) {
        return holdAssetsDao.findById(holdAssetsId).orElse(null);
    }

    public HoldAssets saveHoldAssets(HoldAssets holdAssets) {
        return holdAssetsDao.save(holdAssets);
    }

    public void deleteHoldAssets(Long holdAssetsId) {
        holdAssetsDao.deleteById(holdAssetsId);
    }

    // Other service methods
}