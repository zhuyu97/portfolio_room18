package com.room18.bond.controller;

import com.room18.bond.entity.Bond;
import com.room18.bond.entity.BondDetail;
import com.room18.bond.service.BondDetailService;
import com.room18.bond.service.BondService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bondDetail")
public class BondDetailController {
    @Autowired
    private BondDetailService bondDetailService;

    @GetMapping
    public List<BondDetail> getAllBondDetails() {
        return bondDetailService.getAllBondDetails();
    }

    @GetMapping("/{bondDetailId}")
    public BondDetail getBondById(@PathVariable Long bondDetailId) {
        return bondDetailService.getBondDetailById(bondDetailId);
    }

    @PostMapping("/")
    public BondDetail createBondDetail(@RequestBody BondDetail bondDetail) {
        return bondDetailService.saveBondDetail(bondDetail);
    }

    @PutMapping("/{bondDetailId}")
    public BondDetail updateBondDetail(@PathVariable Long bondDetailId, @RequestBody BondDetail bondDetail) {
        BondDetail existingBondDetail = bondDetailService.getBondDetailById(bondDetailId);
        if (existingBondDetail != null) {
            // 更新现有的债券信息
            BeanUtils.copyProperties(bondDetail, existingBondDetail);
            return bondDetailService.saveBondDetail(existingBondDetail);
        }
        return null;
    }

    @DeleteMapping("/{bondId}")
    public void deleteBondDetail(@PathVariable Long bondDetailId) {
        bondDetailService.deleteBondDetail(bondDetailId);
    }

    // 其他控制器方法
}
