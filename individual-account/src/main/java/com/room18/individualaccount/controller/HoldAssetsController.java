package com.room18.individualaccount.controller;

import com.room18.individualaccount.entity.HoldAssets;
import com.room18.individualaccount.service.HoldAssetsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holdAssets")
public class HoldAssetsController {
    @Autowired
    private HoldAssetsService holdAssetsService;

    @GetMapping("/getAll")
    public List<HoldAssets> getAllHoldAssets() {
        return holdAssetsService.getAllHoldAssets();
    }

    @GetMapping("/{holdAssetsId}")
    public HoldAssets getHoldAssetsById(@PathVariable Long holdAssetsId) {
        return holdAssetsService.getHoldAssetsById(holdAssetsId);
    }

    @PostMapping("/")
    public HoldAssets createHoldAssets(@RequestBody HoldAssets holdAssets) {
        return holdAssetsService.saveHoldAssets(holdAssets);
    }

    @PutMapping("/{holdAssetsId}")
    public HoldAssets updateHoldAssets(@PathVariable Long holdAssetsId, @RequestBody HoldAssets holdAssets) {
        HoldAssets existingHoldAssets = holdAssetsService.getHoldAssetsById(holdAssetsId);
        if (existingHoldAssets != null) {
            // Update existing hold assets
            BeanUtils.copyProperties(holdAssets, existingHoldAssets);
            // Update other fields as needed
            return holdAssetsService.saveHoldAssets(existingHoldAssets);
        }
        return null;
    }

    @DeleteMapping("/{holdAssetsId}")
    public void deleteHoldAssets(@PathVariable Long holdAssetsId) {
        holdAssetsService.deleteHoldAssets(holdAssetsId);
    }

    // Other controller methods
}
