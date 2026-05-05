package com.soccman.common;

import com.soccman.orders.Order;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {

    public boolean requiresApproval(Order order) {
        return order.amount() >= 50_000_000;
    }

    public boolean canCreateOrder(boolean customerOverdue) {
        return !customerOverdue;
    }

    public boolean isTaskCritical(long overdueDays) {
        return overdueDays > 2;
    }
}
