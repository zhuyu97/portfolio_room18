package com.room18.individualaccount.controller;

import com.room18.individualaccount.entity.Cash;
import com.room18.individualaccount.service.CashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cash")
public class CashController {
    @Autowired
    private CashService cashService;

    public CashController(CashService cashService) {
        this.cashService = cashService;
    }

    @GetMapping("/getAll")
    public List<Cash> getAllCash() {
        return cashService.getAllCash();
    }

    @GetMapping("/{cashId}")
    public Cash getCashById(@PathVariable Long cashId) {
        return cashService.getCashById(cashId);
    }

    @PostMapping("/")
    public Cash createCash(@RequestBody Cash cash) {
        return cashService.saveCash(cash);
    }

    @PutMapping("/{cashId}")
    public Cash updateCash(@PathVariable Long cashId, @RequestBody Cash cash) {
        Cash existingCash = cashService.getCashById(cashId);
        if (existingCash != null) {
            // Update the existing cash
            existingCash.setUserId(cash.getUserId());
            existingCash.setAmount(cash.getAmount());
            return cashService.saveCash(existingCash);
        }
        return null;
    }

    @DeleteMapping("/{cashId}")
    public void deleteCash(@PathVariable Long cashId) {
        cashService.deleteCash(cashId);
    }

    // Other controller methods
}
