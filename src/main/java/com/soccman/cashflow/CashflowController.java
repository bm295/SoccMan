package com.soccman.cashflow;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cashflow")
public class CashflowController {

    @GetMapping("/snapshot")
    public CashflowSnapshot snapshot() {
        return new CashflowSnapshot(120_000_000, 75_000_000, 14_000_000, 22_000_000);
    }
}
