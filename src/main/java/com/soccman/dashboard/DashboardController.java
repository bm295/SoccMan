package com.soccman.dashboard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @GetMapping
    public Map<String, Object> summary() {
        return Map.of(
                "monthlyRevenue", 240_000_000,
                "ordersInProgress", 11,
                "lateOrders", 2,
                "newCustomers", 8,
                "overloadedStaff", 1,
                "debtDueThisWeek", 31_000_000
        );
    }
}
