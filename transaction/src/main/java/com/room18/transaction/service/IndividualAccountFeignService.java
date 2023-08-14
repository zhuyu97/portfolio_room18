package com.room18.transaction.service;

import com.room18.common.R;
import com.room18.common.entity.Cash;
import com.room18.common.entity.HoldAssets;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "individualaccount")
public interface IndividualAccountFeignService {
    @GetMapping("/api/cash/getAll")
    public R getAllCash();

    @GetMapping("/api/cash/{cashId}")
    public R getCashById(@PathVariable Long cashId);

    @PostMapping("/api/cash/")
    public R createCash(@RequestBody Cash cash);

    @PutMapping("/api/cash/{cashId}")
    public R updateCash(@PathVariable Long cashId, @RequestBody Cash cash);

    @DeleteMapping("/api/cash/{cashId}")
    public R deleteCash(@PathVariable Long cashId);

    @GetMapping("/api/holdAssets/getAll")
    public R getAllHoldAssets();

    @GetMapping("/api/holdAssets/{holdAssetsId}")
    public R getHoldAssetsById(@PathVariable Long holdAssetsId);

    @PostMapping("/api/holdAssets/")
    public R createHoldAssets(@RequestBody HoldAssets holdAssets);

    @PutMapping("/api/holdAssets/{holdAssetsId}")
    public R updateHoldAssets(@PathVariable Long holdAssetsId, @RequestBody HoldAssets holdAssets);

    @DeleteMapping("/api/holdAssets/{holdAssetsId}")
    public R deleteHoldAssets(@PathVariable Long holdAssetsId);

    @GetMapping("/api/holdAssets/{productionId}/{productionType}")
    public R getHoldAssetsByProductionIdAndType(@PathVariable Long productionId, @PathVariable Integer productionType);

}
