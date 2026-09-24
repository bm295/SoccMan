package com.soccman.operations.application;

import com.soccman.operations.application.port.OperationTaskRepository;
import com.soccman.operations.domain.TaskEscalationPolicy;

import java.util.Optional;

public final class FindMostOverdueTaskService implements FindMostOverdueTaskUseCase {
    private final OperationTaskRepository taskRepository;
    private final TaskEscalationPolicy escalationPolicy;

    public FindMostOverdueTaskService(
            OperationTaskRepository taskRepository,
            TaskEscalationPolicy escalationPolicy
    ) {
        this.taskRepository = taskRepository;
        this.escalationPolicy = escalationPolicy;
    }

    @Override
    public Optional<MostOverdueTaskResult> execute(long escalationThresholdDays) {
        return escalationPolicy.findMostOverdue(
                taskRepository.findAll(),
                escalationThresholdDays
        ).map(task -> new MostOverdueTaskResult(
                task.orderCode(),
                task.taskName(),
                task.overdueDays(),
                task.daysBeyondEscalationThreshold()
        ));
    }
}
