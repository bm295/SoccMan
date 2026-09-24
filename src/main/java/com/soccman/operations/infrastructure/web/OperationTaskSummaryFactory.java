package com.soccman.operations.infrastructure.web;

import com.soccman.operations.domain.OperationTask;
import com.soccman.operations.domain.TaskCriticalPolicy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OperationTaskSummaryFactory {
    private final TaskCriticalPolicy criticalPolicy;

    public OperationTaskSummaryFactory(TaskCriticalPolicy criticalPolicy) {
        this.criticalPolicy = criticalPolicy;
    }

    public Map<String, Object> create(OperationTask task) {
        return Map.of(
                "orderCode", task.orderCode(),
                "taskName", task.taskName(),
                "done", task.done(),
                "critical", criticalPolicy.isCritical(task.overdueDays())
        );
    }
}

