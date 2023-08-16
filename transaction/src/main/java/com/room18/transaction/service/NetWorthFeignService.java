package com.room18.transaction.service;

import com.room18.common.R;
import com.room18.common.entity.NetWorth;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "calnetworth")
public interface NetWorthFeignService {
    @GetMapping("/api/networth/getAll")
    public R getAllNetWorths();

    @PostMapping("/api/networth/")
    public R createNetWorth(@RequestBody NetWorth netWorth);
    @GetMapping("/api/networth/{netWorthId}")
    public R getNetWorthById(@PathVariable("netWorthId") Long id);

    @PutMapping("/api/networth/{netWorthId}")
    public R updateNetWorth(@PathVariable Long netWorthId, @RequestBody NetWorth netWorth);

    @DeleteMapping("/api/networth/{netWorthId}")
    public R deleteNetWorth(@PathVariable Long netWorthId);

    @GetMapping("/api/networth/networthnow")
    public R getCurrentNetWorth();

    @GetMapping("/api/networth/calCurNetWorth")
    public R calCurrentNetWorth();

}
