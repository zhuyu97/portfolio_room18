package com.room18.individualaccount.controller;

import com.room18.common.R;
import com.room18.common.entity.Cash;
import com.room18.individualaccount.service.CashService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Cash data interface")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cash")
public class CashController {
    @Autowired
    private CashService cashService;

    @GetMapping("/getAll")
    public R getAllCash() {
        return R.ok().put("data",cashService.getAllCash());
    }

    @GetMapping("/{cashId}")
    public R getCashById(@PathVariable Long cashId) {
        return R.ok().put("data",cashService.getCashById(cashId));
    }

    @PostMapping("/")
    public R createCash(@RequestBody Cash cash) {
        return R.ok().put("data",cashService.saveCash(cash));
    }

    @PutMapping("/{cashId}")
    public R updateCash(@PathVariable Long cashId, @RequestBody Cash cash) {
        Cash existingCash = cashService.getCashById(cashId);
        if (existingCash != null) {
            // Update the existing cash
            existingCash.setUserId(cash.getUserId());
            existingCash.setAmount(cash.getAmount());
            return R.ok().put("data",cashService.saveCash(existingCash));
        }
        return R.error("The cash id doesn't exist");
    }

    @DeleteMapping("/{cashId}")
    public R deleteCash(@PathVariable Long cashId) {
        cashService.deleteCash(cashId);
        return R.ok().put("message", "Successfully deleted");
    }

    // Other controller methods
}
