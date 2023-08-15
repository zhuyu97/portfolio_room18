package com.room18.transaction.service;

import com.alibaba.fastjson.JSON;
import com.room18.common.Constants;
import com.room18.common.R;
import com.room18.common.VO.BondVO;
import com.room18.common.VO.StockVO;
import com.room18.common.entity.*;
import com.room18.transaction.dao.TransactionRecordDao;
import com.room18.transaction.entity.BuyBondDTO;
import com.room18.transaction.entity.BuyStockDTO;
import com.room18.transaction.entity.SellBondDTO;
import com.room18.transaction.entity.SellStockDTO;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class TransactionRecordService {
    @Autowired
    private TransactionRecordDao transactionRecordDao;

    @Autowired
    private IndividualAccountFeignService indAccountFeignService;

    @Autowired
    private StockFeignService stockFeignService;

    @Autowired
    private NetWorthFeignService netWorthFeignService;

    @Autowired
    private BondFeignService bondFeignService;


    public List<TransactionRecord> getAllTransactionRecords() {
        return transactionRecordDao.findAll();
    }

    public TransactionRecord getTransactionRecordById(Long trId) {
        return transactionRecordDao.findById(trId).orElse(null);
    }

    public TransactionRecord saveTransactionRecord(TransactionRecord transactionRecord) {
        return transactionRecordDao.save(transactionRecord);
    }

    public void deleteTransactionRecord(Long trId) {
        transactionRecordDao.deleteById(trId);
    }

    public HashMap<String, Object> buyStock(BuyStockDTO buyStockDTO){
        HashMap<String, Object> result = new HashMap<>();
        R cashR = indAccountFeignService.getCashById(1L);
        Cash cash = JSON.parseObject(JSON.toJSONString(cashR.get("data")), Cash.class);

        R stockR = stockFeignService.getStockById(buyStockDTO.getStockId());
        StockVO stockVO = JSON.parseObject(JSON.toJSONString(stockR.get("data")), StockVO.class);
        BigDecimal shouldCost = stockVO.getStockPrice().multiply(new BigDecimal(buyStockDTO.getBuyAmount()));
        if(buyStockDTO.getBuyAmount()%100 != 0){
            result.put("success", false);
            result.put("message", "The quantity purchased must be an integer multiple of 100.");
            return result;
        }

        if(shouldCost.compareTo(cash.getAmount()) > 0){
            result.put("success", false);
            result.put("message", "Your balance is not sufficient.");
            return result;
        }
        else {
            //1.change cash
            cash.setAmount(cash.getAmount().subtract(shouldCost));
            indAccountFeignService.updateCash(1L, cash);

            //2.change HoldAssets
            R holdAssetsR = indAccountFeignService.getHoldAssetsByProductionIdAndType(buyStockDTO.getStockId(),
                    Constants.PRODUCTION_TYPE_STOCK);

            //2.1 dont have this production
            if((Integer) holdAssetsR.get("code") != 200) {
                HoldAssets holdAssets = new HoldAssets();
                holdAssets.setUserId(Constants.USER_ID);
                holdAssets.setProductionId(buyStockDTO.getStockId());
                holdAssets.setProductionType(Constants.PRODUCTION_TYPE_STOCK);
                holdAssets.setProductionAmount(buyStockDTO.getBuyAmount());

                R holdAssets1 = indAccountFeignService.createHoldAssets(holdAssets);
            }
            //2.2 have this production
            else {
                HoldAssets holdAssets = JSON.parseObject(JSON.toJSONString(holdAssetsR.get("data")), HoldAssets.class);
                holdAssets.setProductionAmount(holdAssets.getProductionAmount() + buyStockDTO.getBuyAmount());
                R r = indAccountFeignService.updateHoldAssets(holdAssets.getHoldAssetsId(), holdAssets);
            }

            R stockDetailR = stockFeignService.findStockDetailByStockId(buyStockDTO.getStockId());
            StockDetail stockDetail = JSON.parseObject(JSON.toJSONString(stockDetailR.get("data")), StockDetail.class);

            //create transaction
            TransactionRecord transactionRecord = new TransactionRecord();
            transactionRecord.setUserId(Constants.USER_ID);
            transactionRecord.setTransactionType(Constants.TRANSACTION_TYPE_BUY);
            transactionRecord.setNumberOfTransaction(buyStockDTO.getBuyAmount());
            transactionRecord.setProductionId(buyStockDTO.getStockId());
            transactionRecord.setProductionType(Constants.PRODUCTION_TYPE_STOCK);
            transactionRecord.setProductionDetailId(stockDetail.getStockDetailId());
            transactionRecord.setProductionPrice(stockVO.getStockPrice());
            transactionRecord.setCost(shouldCost);
            transactionRecord.setRemainCash(cash.getAmount());
            transactionRecord.setTime(LocalDateTime.now());
            TransactionRecord transactionRecord1 = this.saveTransactionRecord(transactionRecord);

            //update stock price
            StockDetail stockDetailNow = new StockDetail();
            stockDetailNow.setStockId(stockVO.getStockId());
            stockDetailNow.setStockPrice(stockVO.getStockPrice());
            stockDetailNow.setTime(LocalDateTime.now());
            stockFeignService.createStockDetail(stockDetailNow);

            //calculate current net worth
//            R r = netWorthFeignService.calCurrentNetWorth();
            result.put("success", true);
            result.put("message", "Successful transaction.");
            return result;
        }
    }


    public HashMap<String, Object> sellStock(SellStockDTO sellStockDTO){
        HashMap<String, Object> result = new HashMap<>();
        //get the stock detail user want to sell
        R stockR = stockFeignService.getStockById(sellStockDTO.getStockId());
        StockVO stockVO = JSON.parseObject(JSON.toJSONString(stockR.get("data")), StockVO.class);

        //get the holdAssets of user
        R holdAssetsR = indAccountFeignService.getHoldAssetsByProductionIdAndType(sellStockDTO.getStockId(), Constants.PRODUCTION_TYPE_STOCK);
        HoldAssets holdAssets = JSON.parseObject(JSON.toJSONString(holdAssetsR.get("data")), HoldAssets.class);

        //begin transaction
        if(sellStockDTO.getSellAmount()%100 != 0){
            result.put("success", false);
            result.put("message", "The quantity purchased must be an integer multiple of 100.");
            return result;
        }

        if(sellStockDTO.getSellAmount() > holdAssets.getProductionAmount()){
            result.put("success", false);
            result.put("message", "Transaction failed. The quantity of stocks you sold exceeds the total quantity owned.");
            return result;
        }
        else {
            //update holdAssets
            holdAssets.setProductionAmount(holdAssets.getProductionAmount() - sellStockDTO.getSellAmount());
            R r = indAccountFeignService.updateHoldAssets(holdAssets.getHoldAssetsId(), holdAssets);

            //update cash
            R cashR = indAccountFeignService.getCashById(1L);
            Cash cash = JSON.parseObject(JSON.toJSONString(cashR.get("data")), Cash.class);
            BigDecimal incomingFunds = BigDecimal.valueOf(sellStockDTO.getSellPrice() * sellStockDTO.getSellAmount());
            cash.setAmount(cash.getAmount().add(incomingFunds));
            indAccountFeignService.updateCash(1L, cash);


            R stockDetailR = stockFeignService.findStockDetailByStockId(sellStockDTO.getStockId());
            StockDetail stockDetail = JSON.parseObject(JSON.toJSONString(stockDetailR.get("data")), StockDetail.class);

            //create transaction record
            TransactionRecord transactionRecord = new TransactionRecord();
            transactionRecord.setUserId(Constants.USER_ID);
            transactionRecord.setTransactionType(Constants.TRANSACTION_TYPE_SELL);
            transactionRecord.setNumberOfTransaction(sellStockDTO.getSellAmount());
            transactionRecord.setProductionId(sellStockDTO.getStockId());
            transactionRecord.setProductionType(Constants.PRODUCTION_TYPE_STOCK);
            transactionRecord.setProductionDetailId(stockDetail.getStockDetailId());
            transactionRecord.setProductionPrice(BigDecimal.valueOf(sellStockDTO.getSellPrice()));
            transactionRecord.setCost(incomingFunds);
            transactionRecord.setRemainCash(cash.getAmount());
            transactionRecord.setTime(LocalDateTime.now());
            TransactionRecord transactionRecord1 = this.saveTransactionRecord(transactionRecord);

            //change price of stock
            StockDetail stockDetailNow = new StockDetail();
            stockDetailNow.setStockId(stockVO.getStockId());
            stockDetailNow.setStockPrice(stockVO.getStockPrice());
            stockDetailNow.setTime(LocalDateTime.now());
            stockFeignService.createStockDetail(stockDetailNow);

            //calculate current net worth
//            R r = netWorthFeignService.calCurrentNetWorth();
            result.put("success", true);
            result.put("message", "Successful transaction.");
            return result;
        }
    }



    public HashMap<String, Object> buyBond(BuyBondDTO buyBondDTO){
        HashMap<String, Object> result = new HashMap<>();
        R cashR = indAccountFeignService.getCashById(1L);
        Cash cash = JSON.parseObject(JSON.toJSONString(cashR.get("data")), Cash.class);

        R bondR = bondFeignService.getBondById(buyBondDTO.getBondId());
        BondVO bondVO = JSON.parseObject(JSON.toJSONString(bondR.get("data")), BondVO.class);
        BigDecimal shouldCost = bondVO.getBondPrice().multiply(new BigDecimal(buyBondDTO.getBuyAmount()));
        if(buyBondDTO.getBuyAmount() <= 0){
            result.put("success", false);
            result.put("message", "The quantity purchased must be larger than 0.");
            return result;
        }

        if(shouldCost.compareTo(cash.getAmount()) > 0){
            result.put("success", false);
            result.put("message", "Your balance is not sufficient.");
            return result;
        }
        else {
            //1.change cash
            cash.setAmount(cash.getAmount().subtract(shouldCost));
            indAccountFeignService.updateCash(1L, cash);

            //2.change HoldAssets
            R holdAssetsR = indAccountFeignService.getHoldAssetsByProductionIdAndType(buyBondDTO.getBondId(),
                    Constants.PRODUCTION_TYPE_BOND);

            //2.1 dont have this production
            if((Integer) holdAssetsR.get("code") != 200) {
                HoldAssets holdAssets = new HoldAssets();
                holdAssets.setUserId(Constants.USER_ID);
                holdAssets.setProductionId(buyBondDTO.getBondId());
                holdAssets.setProductionType(Constants.PRODUCTION_TYPE_BOND);
                holdAssets.setProductionAmount(buyBondDTO.getBuyAmount());

                R holdAssets1 = indAccountFeignService.createHoldAssets(holdAssets);
            }
            //2.2 have this production
            else {
                HoldAssets holdAssets = JSON.parseObject(JSON.toJSONString(holdAssetsR.get("data")), HoldAssets.class);
                holdAssets.setProductionAmount(holdAssets.getProductionAmount() + buyBondDTO.getBuyAmount());
                R r = indAccountFeignService.updateHoldAssets(holdAssets.getHoldAssetsId(), holdAssets);
            }

            R bondDetailR = bondFeignService.findBondDetailByBondId(buyBondDTO.getBondId());
            BondDetail bondDetail = JSON.parseObject(JSON.toJSONString(bondDetailR.get("data")), BondDetail.class);

            //create transaction
            TransactionRecord transactionRecord = new TransactionRecord();
            transactionRecord.setUserId(Constants.USER_ID);
            transactionRecord.setTransactionType(Constants.TRANSACTION_TYPE_BUY);
            transactionRecord.setNumberOfTransaction(buyBondDTO.getBuyAmount());
            transactionRecord.setProductionId(buyBondDTO.getBondId());
            transactionRecord.setProductionType(Constants.PRODUCTION_TYPE_STOCK);
            transactionRecord.setProductionDetailId(bondDetail.getBondDetailId());
            transactionRecord.setProductionPrice(bondVO.getBondPrice());
            transactionRecord.setCost(shouldCost);
            transactionRecord.setRemainCash(cash.getAmount());
            transactionRecord.setTime(LocalDateTime.now());
            TransactionRecord transactionRecord1 = this.saveTransactionRecord(transactionRecord);


            //calculate current net worth
//            R r = netWorthFeignService.calCurrentNetWorth();
            result.put("success", true);
            result.put("message", "Successful transaction.");
            return result;
        }
    }


    public HashMap<String, Object> sellBond(SellBondDTO sellBondDTO){
        HashMap<String, Object> result = new HashMap<>();
        //get the bond detail user want to sell
        R bondR = bondFeignService.getBondById(sellBondDTO.getBondId());
        BondVO bondVO = JSON.parseObject(JSON.toJSONString(bondR.get("data")), BondVO.class);

        //get the holdAssets of user
        R holdAssetsR = indAccountFeignService.getHoldAssetsByProductionIdAndType(sellBondDTO.getBondId(), Constants.PRODUCTION_TYPE_BOND);
        HoldAssets holdAssets = JSON.parseObject(JSON.toJSONString(holdAssetsR.get("data")), HoldAssets.class);

        //begin transaction
        if(sellBondDTO.getSellAmount() <= 0){
            result.put("success", false);
            result.put("message", "The quantity selled must be larger than 0.");
            return result;
        }

        if(sellBondDTO.getSellAmount() > holdAssets.getProductionAmount()){
            result.put("success", false);
            result.put("message", "Transaction failed. The quantity of bonds you sold exceeds the total quantity owned.");
            return result;
        }
        else {
            //update holdAssets
            holdAssets.setProductionAmount(holdAssets.getProductionAmount() - sellBondDTO.getSellAmount());
            R r = indAccountFeignService.updateHoldAssets(holdAssets.getHoldAssetsId(), holdAssets);

            //update cash
            R cashR = indAccountFeignService.getCashById(1L);
            Cash cash = JSON.parseObject(JSON.toJSONString(cashR.get("data")), Cash.class);
            BigDecimal incomingFunds = BigDecimal.valueOf(sellBondDTO.getSellPrice() * sellBondDTO.getSellAmount());
            cash.setAmount(cash.getAmount().add(incomingFunds));
            indAccountFeignService.updateCash(1L, cash);


            R bondDetailR = bondFeignService.findBondDetailByBondId(sellBondDTO.getBondId());
            BondDetail bondDetail = JSON.parseObject(JSON.toJSONString(bondDetailR.get("data")), BondDetail.class);

            //create transaction record
            TransactionRecord transactionRecord = new TransactionRecord();
            transactionRecord.setUserId(Constants.USER_ID);
            transactionRecord.setTransactionType(Constants.TRANSACTION_TYPE_SELL);
            transactionRecord.setNumberOfTransaction(sellBondDTO.getSellAmount());
            transactionRecord.setProductionId(sellBondDTO.getBondId());
            transactionRecord.setProductionType(Constants.PRODUCTION_TYPE_BOND);
            transactionRecord.setProductionDetailId(bondDetail.getBondDetailId());
            transactionRecord.setProductionPrice(BigDecimal.valueOf(sellBondDTO.getSellPrice()));
            transactionRecord.setCost(incomingFunds);
            transactionRecord.setRemainCash(cash.getAmount());
            transactionRecord.setTime(LocalDateTime.now());
            TransactionRecord transactionRecord1 = this.saveTransactionRecord(transactionRecord);


            //calculate current net worth
//            R r = netWorthFeignService.calCurrentNetWorth();
            result.put("success", true);
            result.put("message", "Successful transaction.");
            return result;
        }
    }
    // Other service methods
}

