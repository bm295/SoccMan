package com.soccman.dashboard.application;

import com.soccman.operations.application.MostOverdueTaskResult;

public record DashboardSummary(
        long monthlyRevenue,
        int ordersInProgress,
        int lateOrders,
        int newCustomers,
        int overloadedStaff,
        long debtDueThisWeek,
        MostOverdueTaskResult mostOverdueTask
) {
}

