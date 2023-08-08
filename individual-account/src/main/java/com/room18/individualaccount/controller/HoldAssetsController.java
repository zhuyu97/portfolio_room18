package com.room18.individualaccount.controller;

import com.room18.common.R;
import com.room18.individualaccount.entity.HoldAssets;
import com.room18.individualaccount.service.HoldAssetsService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "Hold Assets data interface")
@RestController
@RequestMapping("/api/holdAssets")
public class HoldAssetsController {
    @Autowired
    private HoldAssetsService holdAssetsService;

    @GetMapping("/getAll")
    public R getAllHoldAssets() {
        return R.ok().put("data",holdAssetsService.getAllHoldAssets());
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

    // Other controller methods
}
