package com.room18.individualaccount.service;

import com.room18.common.R;
import com.room18.common.entity.Bond;
import com.room18.common.entity.BondDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "bond")
public interface BondFeignService {
    @GetMapping("/api/bond/getAll")
    public R getAllBonds();

    @GetMapping("/api/bond/{bondId}")
    public R getBondById(@PathVariable Long bondId);

    @PostMapping("/api/bond/")
    public R createBond(@RequestBody Bond bond);

    @PutMapping("/api/bond/{bondId}")
    public R updateBond(@PathVariable Long bondId, @RequestBody Bond bond);

    @DeleteMapping("/api/bond/{bondId}")
    public R deleteBond(@PathVariable Long bondId);

    @GetMapping("/api/bond/fuzzyQuery/{queryString}")
    public R getBondByFuzzyQuery(@PathVariable("queryString") String queryString);

    @GetMapping("/api/bondDetail/getAll")
    public R getAllBondDetails();

    @GetMapping("/api/bondDetail/{bondDetailId}")
    public R getBondDetailById(@PathVariable Long bondDetailId);

    @PostMapping("/api/bondDetail/")
    public R createBondDetail(@RequestBody BondDetail bondDetail);

    @PutMapping("/api/bondDetail/{bondDetailId}")
    public R updateBondDetail(@PathVariable Long bondDetailId, @RequestBody BondDetail bondDetail);

    @DeleteMapping("/api/bondDetail/{bondId}")
    public R deleteBondDetail(@PathVariable Long bondDetailId);

    @GetMapping("/api/bondDetail/findBondDetailByBondId/{bondId}")
    public R findBondDetailByBondId(@PathVariable Long bondId);
}
