package com.soccman.operations;

import com.soccman.common.PolicyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/operations")
public class OperationsController {
    private final PolicyService policyService;

    public OperationsController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping("/tasks")
    public List<Map<String, Object>> tasks() {
        var tasks = List.of(
                new OperationTask("SO-001", "Call customer", true, 0),
                new OperationTask("SO-001", "Prepare package", false, 3),
                new OperationTask("SO-001", "Confirm delivery", false, 1),
                new OperationTask("SO-001", "Issue invoice", false, 0)
        );

        return tasks.stream().map(task -> Map.of(
                "orderCode", task.orderCode(),
                "taskName", task.taskName(),
                "done", task.done(),
                "critical", policyService.isTaskCritical(task.overdueDays())
        )).collect(Collectors.toList());
    }
}
