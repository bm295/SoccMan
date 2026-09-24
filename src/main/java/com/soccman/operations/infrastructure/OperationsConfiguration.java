package com.soccman.operations.infrastructure;

import com.soccman.operations.application.FindMostOverdueTaskService;
import com.soccman.operations.application.FindMostOverdueTaskUseCase;
import com.soccman.operations.application.ListOperationTasksService;
import com.soccman.operations.application.ListOperationTasksUseCase;
import com.soccman.operations.application.port.OperationTaskRepository;
import com.soccman.operations.domain.TaskEscalationPolicy;
import com.soccman.operations.domain.TaskCriticalPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OperationsConfiguration {

    @Bean
    FindMostOverdueTaskUseCase findMostOverdueTaskUseCase(
            OperationTaskRepository taskRepository
    ) {
        return new FindMostOverdueTaskService(
                taskRepository,
                new TaskEscalationPolicy()
        );
    }

    @Bean
    ListOperationTasksUseCase listOperationTasksUseCase(
            OperationTaskRepository taskRepository
    ) {
        return new ListOperationTasksService(taskRepository);
    }

    @Bean
    TaskCriticalPolicy taskCriticalPolicy() {
        return new TaskCriticalPolicy();
    }
}
