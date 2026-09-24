package com.soccman.dashboard;

import com.soccman.dashboard.application.DashboardSummary;
import com.soccman.dashboard.application.GetDashboardSummaryUseCase;
import com.soccman.dashboard.infrastructure.web.DashboardController;
import com.soccman.operations.application.FindMostOverdueTaskService;
import com.soccman.operations.domain.TaskEscalationPolicy;
import com.soccman.operations.infrastructure.persistence.InMemoryOperationTaskRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DashboardControllerTest {

    @Test
    void summaryIncludesMostOverdueTask() {
        var controller = createController();

        DashboardSummary summary = controller.summary();

        assertEquals("Prepare package", summary.mostOverdueTask().taskName());
        assertEquals(3, summary.mostOverdueTask().overdueDays());
        assertEquals(2, summary.mostOverdueTask().daysBeyondEscalationThreshold());
    }

    @Test
    void summaryPreservesExistingDashboardMetrics() {
        var controller = createController();

        DashboardSummary summary = controller.summary();

        assertEquals(240_000_000, summary.monthlyRevenue());
        assertEquals(11, summary.ordersInProgress());
        assertEquals(2, summary.lateOrders());
        assertEquals(8, summary.newCustomers());
        assertEquals(1, summary.overloadedStaff());
        assertEquals(31_000_000, summary.debtDueThisWeek());
    }

    private DashboardController createController() {
        var findMostOverdueTask = new FindMostOverdueTaskService(
                new InMemoryOperationTaskRepository(),
                new TaskEscalationPolicy()
        );
        return new DashboardController(
                new GetDashboardSummaryUseCase(findMostOverdueTask)
        );
    }
}
