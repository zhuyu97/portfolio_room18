package com.room18.bond.controller;

import com.room18.common.entity.BondDetail;
import com.room18.bond.service.BondDetailService;
import com.room18.common.R;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "BondDetail data interface")
@RestController
@RequestMapping("/api/bondDetail")
public class BondDetailController {
    @Autowired
    private BondDetailService bondDetailService;

    @GetMapping("/getAll")
    public R getAllBondDetails() {
        return R.ok().put("data",bondDetailService.getAllBondDetails());
    }

    @GetMapping("/{bondDetailId}")
    public R getBondById(@PathVariable Long bondDetailId) {
        return R.ok().put("data",bondDetailService.getBondDetailById(bondDetailId));
    }

    @PostMapping("/")
    public R createBondDetail(@RequestBody BondDetail bondDetail) {
        return R.ok().put("data",bondDetailService.saveBondDetail(bondDetail));
    }

    @PutMapping("/{bondDetailId}")
    public R updateBondDetail(@PathVariable Long bondDetailId, @RequestBody BondDetail bondDetail) {
        BondDetail existingBondDetail = bondDetailService.getBondDetailById(bondDetailId);
        if (existingBondDetail != null) {
            // 更新现有的债券信息
            BeanUtils.copyProperties(bondDetail, existingBondDetail);
            return R.ok().put("data",bondDetailService.saveBondDetail(existingBondDetail));
        }
        return R.error("The bondDetail id doesn't exist");
    }

    @DeleteMapping("/{bondId}")
    public R deleteBondDetail(@PathVariable Long bondDetailId) {
        bondDetailService.deleteBondDetail(bondDetailId);
        return R.ok().put("message", "Successfully deleted");
    }
    // 其他控制器方法
}
