package com.room18.bond.controller;

import com.room18.bond.entity.Bond;
import com.room18.bond.service.BondService;
import com.room18.common.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "Bond data interface")
@RestController
@RequestMapping("/api/bond")
public class BondController {
    @Autowired
    private BondService bondService;

    @GetMapping("/getAll")
    public R getAllBonds() {
        return R.ok().put("data", bondService.getAllBonds());
    }

    @GetMapping("/{bondId}")
    public R getBondById(@PathVariable Long bondId) {
        return R.ok().put("data", bondService.getBondById(bondId));
    }

    @PostMapping("/")
    public R createBond(@RequestBody Bond bond) {
        return R.ok().put("data",bondService.saveBond(bond));
    }

    @PutMapping("/{bondId}")
    public R updateBond(@PathVariable Long bondId, @RequestBody Bond bond) {
        Bond existingBond = bondService.getBondById(bondId);
        if (existingBond != null) {
            // 更新现有的债券信息
            existingBond.setBondName(bond.getBondName());
            return R.ok().put("data",bondService.saveBond(existingBond));
        }
        return R.error("The bond id doesn't exist");
    }

    @DeleteMapping("/{bondId}")
    public R deleteBond(@PathVariable Long bondId) {
        bondService.deleteBond(bondId);
        return R.ok().put("message", "Successfully deleted");
    }

    // 其他控制器方法
}
