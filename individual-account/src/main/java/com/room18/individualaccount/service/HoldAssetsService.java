package com.room18.individualaccount.service;

import com.alibaba.fastjson.JSON;
import com.room18.common.Constants;
import com.room18.common.R;
import com.room18.common.VO.BondVO;
import com.room18.common.VO.StockVO;
import com.room18.common.entity.Cash;
import com.room18.individualaccount.dao.HoldAssetsDao;
import com.room18.common.entity.HoldAssets;
import com.room18.common.VO.HoldAssetsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class HoldAssetsService {
    @Autowired
    private HoldAssetsDao holdAssetsDao;

    @Autowired
    private CashService cashService;

    @Autowired
    private StockFeignService stockFeignService;

    @Autowired
    private BondFeignService bondFeignService;

    public List<HoldAssetsVO> getAllHoldAssets() {
        List<HoldAssets> holdAssetsList = holdAssetsDao.findAll();
        List<HoldAssetsVO> holdAssetsVOList = new ArrayList<>();
        if(holdAssetsList.size() > 0) {
            for(HoldAssets holdAssets: holdAssetsList){
                if(holdAssets.getProductionType() == Constants.PRODUCTION_TYPE_STOCK){
                    R stockR = stockFeignService.getStockById(holdAssets.getProductionId());
                    StockVO stockVO = JSON.parseObject(JSON.toJSONString(stockR.get("data")), StockVO.class);
                    HoldAssetsVO holdAssetsVO = new HoldAssetsVO();
                    BeanUtils.copyProperties(holdAssets, holdAssetsVO);
                    holdAssetsVO.setProductionTypeName("Stock");
                    holdAssetsVO.setHoldingCost(holdAssets.getCost().divide(BigDecimal.valueOf(holdAssetsVO.getProductionAmount()),0, BigDecimal.ROUND_HALF_UP));
                    BigDecimal curValue = stockVO.getStockPrice().multiply(BigDecimal.valueOf(holdAssetsVO.getProductionAmount()));
                    BigDecimal income = curValue.subtract(holdAssets.getCost());
                    holdAssetsVO.setIncome(income);
                    holdAssetsVO.setIncomeRate(holdAssetsVO.getHoldingCost().divide(stockVO.getStockPrice(),0, BigDecimal.ROUND_HALF_UP)
                            .negate()
                            .doubleValue());
                    holdAssetsVO.setProductName(stockVO.getStockName());
                    holdAssetsVOList.add(holdAssetsVO);
                }
                else {
                    R bondR = bondFeignService.getBondById(holdAssets.getProductionId());
                    BondVO bondVO = JSON.parseObject(JSON.toJSONString(bondR.get("data")), BondVO.class);
                    HoldAssetsVO holdAssetsVO = new HoldAssetsVO();
                    BeanUtils.copyProperties(holdAssets, holdAssetsVO);
                    holdAssetsVO.setProductionTypeName("Bond");
                    holdAssetsVO.setHoldingCost(holdAssets.getCost().divide(BigDecimal.valueOf(holdAssetsVO.getProductionAmount()),0, BigDecimal.ROUND_HALF_UP));
                    BigDecimal curValue = bondVO.getBondPrice().multiply(BigDecimal.valueOf(holdAssetsVO.getProductionAmount()));
                    BigDecimal income = curValue.subtract(holdAssets.getCost());
                    holdAssetsVO.setIncome(income);
                    holdAssetsVO.setIncomeRate(holdAssetsVO.getHoldingCost().divide(bondVO.getBondPrice(),0, BigDecimal.ROUND_HALF_UP).negate().doubleValue());
                    holdAssetsVO.setProductName(bondVO.getBondName());
                    holdAssetsVOList.add(holdAssetsVO);
                }
            }
        }
        return holdAssetsVOList;
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

    public HoldAssets getHoldAssetsByProductionIdAndType(Long productionId, Integer productionType){
        HoldAssets holdAssets = holdAssetsDao.getHoldAssetsByProductionIdAndType(productionId, productionType);
        return holdAssets;
    }

    public List<BigDecimal> getAllAssetsValue() {
        List<BigDecimal> result = new ArrayList<>();

        List<HoldAssetsVO> allHoldAssets = getAllHoldAssets();
        //cash
        Cash cash = cashService.getCashById(1L);
        result.add(cash.getAmount());

        //stock
        //bond
        BigDecimal stockValue = new BigDecimal(0);
        BigDecimal bondValue = new BigDecimal(0);
        for(HoldAssetsVO holdAssetsVO: allHoldAssets){
            if(holdAssetsVO.getProductionTypeName().equalsIgnoreCase("stock")){
                R stockR = stockFeignService.getStockById(holdAssetsVO.getProductionId());
                StockVO stockVO = JSON.parseObject(JSON.toJSONString(stockR.get("data")), StockVO.class);
                stockValue = stockValue.add(stockVO.getStockPrice().multiply(BigDecimal.valueOf(holdAssetsVO.getProductionAmount())));
            }
            else if(holdAssetsVO.getProductionTypeName().equalsIgnoreCase("bond")){
                R bondR = bondFeignService.getBondById(holdAssetsVO.getProductionId());
                BondVO bondVO = JSON.parseObject(JSON.toJSONString(bondR.get("data")), BondVO.class);
                bondValue = bondValue.add(bondVO.getBondPrice().multiply(BigDecimal.valueOf(holdAssetsVO.getProductionAmount())));
            }
        }

        result.add(stockValue);
        result.add(bondValue);
        return result;
    }

    // Other service methods
}