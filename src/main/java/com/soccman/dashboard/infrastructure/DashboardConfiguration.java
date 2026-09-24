package com.soccman.dashboard.infrastructure;

import com.soccman.dashboard.application.GetDashboardSummaryUseCase;
import com.soccman.operations.application.FindMostOverdueTaskUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DashboardConfiguration {

    @Bean
    GetDashboardSummaryUseCase getDashboardSummaryUseCase(
            FindMostOverdueTaskUseCase findMostOverdueTask
    ) {
        return new GetDashboardSummaryUseCase(findMostOverdueTask);
    }
}

