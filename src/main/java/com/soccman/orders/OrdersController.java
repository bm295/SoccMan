package com.soccman.orders;

import com.soccman.common.PolicyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    private final PolicyService policyService;

    public OrdersController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping
    public List<Order> orders() {
        return List.of(
                new Order("SO-001", "C-001", 62_000_000, OrderStatus.CONFIRMED),
                new Order("SO-002", "C-002", 18_000_000, OrderStatus.IN_PROGRESS)
        );
    }

    @GetMapping("/approval-check")
    public Map<String, Boolean> approvalCheck(@RequestParam long amount) {
        var order = new Order("TMP", "C-000", amount, OrderStatus.DRAFT);
        return Map.of("requiresApproval", policyService.requiresApproval(order));
    }
}
