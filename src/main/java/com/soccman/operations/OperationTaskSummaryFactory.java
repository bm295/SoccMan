package com.soccman.operations;

import com.soccman.common.PolicyService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OperationTaskSummaryFactory {
    private final PolicyService policyService;

    public OperationTaskSummaryFactory(PolicyService policyService) {
        this.policyService = policyService;
    }

    public Map<String, Object> create(OperationTask task) {
        return Map.of(
                "orderCode", task.orderCode(),
                "taskName", task.taskName(),
                "done", task.done(),
                "critical", policyService.isTaskCritical(task.overdueDays())
        );
    }
}
