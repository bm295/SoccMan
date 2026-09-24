package com.soccman.dashboard.application;

import com.soccman.operations.application.FindMostOverdueTaskUseCase;

public final class GetDashboardSummaryUseCase {
    private static final long TASK_ESCALATION_THRESHOLD_DAYS = 1;

    private final FindMostOverdueTaskUseCase findMostOverdueTask;

    public GetDashboardSummaryUseCase(FindMostOverdueTaskUseCase findMostOverdueTask) {
        this.findMostOverdueTask = findMostOverdueTask;
    }

    public DashboardSummary execute() {
        return new DashboardSummary(
                240_000_000,
                11,
                2,
                8,
                1,
                31_000_000,
                findMostOverdueTask.execute(TASK_ESCALATION_THRESHOLD_DAYS).orElse(null)
        );
    }
}

