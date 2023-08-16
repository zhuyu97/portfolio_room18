package com.room18.calnetworth.controller;

import com.room18.calnetworth.service.NetWorthService;
import com.room18.common.R;
import com.room18.common.entity.BondDetail;
import com.room18.common.entity.NetWorth;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/networth")
public class NetWorthController {
    @Autowired
    private NetWorthService netWorthService;

    @GetMapping("/getAll")
    public R getAllNetWorths() {
        return R.ok().put("data",netWorthService.getAllNetWorthsOrderByTime());
    }

    @PostMapping("/")
    public R createNetWorth(@RequestBody NetWorth netWorth) {
        NetWorth savedNetWorth = netWorthService.saveNetWorth(netWorth);
        return R.ok().put("data", savedNetWorth);
    }

    @GetMapping("/{netWorthId}")
    public R getNetWorthById(@PathVariable("netWorthId") Long id) {
        return R.ok().put("data", netWorthService.getNetWorthById(id));
    }

    @PutMapping("/{netWorthId}")
    public R updateNetWorth(@PathVariable Long netWorthId, @RequestBody NetWorth netWorth) {
        NetWorth existNetWorth = netWorthService.getNetWorthById(netWorthId);
        if (existNetWorth != null) {
            BeanUtils.copyProperties(netWorth, existNetWorth);
            return R.ok().put("data",netWorthService.saveNetWorth(existNetWorth));
        }
        return R.error("The netWorth id doesn't exist");
    }

    @DeleteMapping("/{netWorthId}")
    public R deleteNetWorth(@PathVariable Long netWorthId) {
        netWorthService.deleteNetWorth(netWorthId);
        return R.ok().put("message", "Successfully deleted");
    }

    @GetMapping("/networthnow")
    public R getCurrentNetWorth(){
        NetWorth currentNetWorth = netWorthService.getCurrentNetWorth();
        return R.ok().put("data", currentNetWorth);
    }

    @GetMapping("/calCurNetWorth")
    public R calCurrentNetWorth() {
        NetWorth netWorth = netWorthService.calCurrentNetWorth();
        return R.ok().put("data", netWorth);
    }
    // Additional methods can be added based on your requirements
}