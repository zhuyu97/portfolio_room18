package com.room18.individualaccount.controller;

import com.room18.common.R;
import com.room18.common.entity.HoldAssets;
import com.room18.common.VO.HoldAssetsVO;
import com.room18.individualaccount.service.HoldAssetsService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Api(tags = "Hold Assets data interface")
@RestController
@RequestMapping("/api/holdAssets")
public class HoldAssetsController {
    @Autowired
    private HoldAssetsService holdAssetsService;

    @GetMapping("/getAll")
    public R getAllHoldAssets() {
        List<HoldAssetsVO> allHoldAssets = holdAssetsService.getAllHoldAssets();
        if(allHoldAssets.size() > 0){
            return R.ok().put("data", allHoldAssets);
        }
        else {
            return R.error(404, "No holdAssets found.");
        }
    }

    @GetMapping("/{holdAssetsId}")
    public R getHoldAssetsById(@PathVariable Long holdAssetsId) {
        return R.ok().put("data",holdAssetsService.getHoldAssetsById(holdAssetsId));
    }

    @PostMapping("/")
    public R createHoldAssets(@RequestBody HoldAssets holdAssets) {
        return R.ok().put("data",holdAssetsService.saveHoldAssets(holdAssets));
    }

    @PutMapping("/{holdAssetsId}")
    public R updateHoldAssets(@PathVariable Long holdAssetsId, @RequestBody HoldAssets holdAssets) {
        HoldAssets existingHoldAssets = holdAssetsService.getHoldAssetsById(holdAssetsId);
        if (existingHoldAssets != null) {
            // Update existing hold assets
            BeanUtils.copyProperties(holdAssets, existingHoldAssets);
            // Update other fields as needed
            return R.ok().put("data",holdAssetsService.saveHoldAssets(existingHoldAssets));
        }
        return R.error("The hostAssets id doesn't exist");
    }

    @DeleteMapping("/{holdAssetsId}")
    public R deleteHoldAssets(@PathVariable Long holdAssetsId) {
        holdAssetsService.deleteHoldAssets(holdAssetsId);
        return R.ok().put("message", "Successfully deleted");
    }

    @GetMapping("/{productionId}/{productionType}")
    public R getHoldAssetsByProductionIdAndType(@PathVariable Long productionId, @PathVariable Integer productionType) {
        HoldAssets holdAssets = holdAssetsService.getHoldAssetsByProductionIdAndType(productionId, productionType);
        if(holdAssets != null){
            return R.ok().put("data", holdAssets);
        }
        else {
            return R.error(404, "There is no hold assets with that production id and type.");
        }
    }
    
    @GetMapping("/getAllAssetsValue")
    public R getAllAssetsValue(){
        HashMap<String, BigDecimal> allAssetsValue = holdAssetsService.getAllAssetsValue();
        return R.ok().put("data", allAssetsValue);
    }


    // Other controller methods
}
