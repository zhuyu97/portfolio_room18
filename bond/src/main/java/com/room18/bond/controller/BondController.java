package com.room18.bond.controller;

import com.room18.bond.entity.Bond;
import com.room18.bond.service.BondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bond")
public class BondController {
    @Autowired
    private BondService bondService;


    public BondController(BondService bondsService) {
        this.bondService = bondsService;
    }

    @GetMapping
    public List<Bond> getAllBonds() {
        return bondService.getAllBonds();
    }

    @GetMapping("/{bondId}")
    public Bond getBondById(@PathVariable Long bondId) {
        return bondService.getBondById(bondId);
    }

    @PostMapping
    public Bond createBond(@RequestBody Bond bond) {
        return bondService.saveBond(bond);
    }

    @PutMapping("/{bondId}")
    public Bond updateBond(@PathVariable Long bondId, @RequestBody Bond bond) {
        Bond existingBond = bondService.getBondById(bondId);
        if (existingBond != null) {
            // 更新现有的债券信息
            existingBond.setBondName(bond.getBondName());
            return bondService.saveBond(existingBond);
        }
        return null;
    }

    @DeleteMapping("/{bondId}")
    public void deleteBond(@PathVariable Long bondId) {
        bondService.deleteBond(bondId);
    }

    // 其他控制器方法
}
