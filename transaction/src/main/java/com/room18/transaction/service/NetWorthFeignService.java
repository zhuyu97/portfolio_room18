package com.room18.transaction.service;

import com.room18.common.R;
import com.room18.common.entity.NetWorth;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "calnetworth")
public interface NetWorthFeignService {
    @GetMapping("/getAll")
    public R getAllNetWorths();

    @PostMapping("/")
    public R createNetWorth(@RequestBody NetWorth netWorth);
    @GetMapping("/{netWorthId}")
    public R getNetWorthById(@PathVariable("netWorthId") Long id);

    @PutMapping("/{netWorthId}")
    public R updateNetWorth(@PathVariable Long netWorthId, @RequestBody NetWorth netWorth);

    @DeleteMapping("/{netWorthId}")
    public R deleteNetWorth(@PathVariable Long netWorthId);

    @GetMapping("/networthnow")
    public R getCurrentNetWorth();

    @GetMapping("/calCurNetWorth")
    public R calCurrentNetWorth();

}
