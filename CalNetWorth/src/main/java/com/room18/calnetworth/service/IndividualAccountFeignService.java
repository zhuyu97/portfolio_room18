package com.room18.calnetworth.service;

import com.room18.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "individualaccount")
public interface IndividualAccountFeignService {
    @GetMapping("/api/holdAssets/getAllAssetsValue")
    public R getAllAssetsValue();
}
