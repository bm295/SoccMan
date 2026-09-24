package com.soccman.dashboard.infrastructure.web;

import com.soccman.dashboard.application.DashboardSummary;
import com.soccman.dashboard.application.GetDashboardSummaryUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final GetDashboardSummaryUseCase getDashboardSummary;

    public DashboardController(GetDashboardSummaryUseCase getDashboardSummary) {
        this.getDashboardSummary = getDashboardSummary;
    }

    @GetMapping
    public DashboardSummary summary() {
        return getDashboardSummary.execute();
    }
}
