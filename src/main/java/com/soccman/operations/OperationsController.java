package com.soccman.operations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/operations")
public class OperationsController {
    private final OperationTaskSummaryFactory taskSummaryFactory;

    public OperationsController(OperationTaskSummaryFactory taskSummaryFactory) {
        this.taskSummaryFactory = taskSummaryFactory;
    }

    @GetMapping("/tasks")
    public List<Map<String, Object>> tasks() {
        var tasks = List.of(
                new OperationTask("SO-001", "Call customer", true, 0),
                new OperationTask("SO-001", "Prepare package", false, 3),
                new OperationTask("SO-001", "Confirm delivery", false, 1),
                new OperationTask("SO-001", "Issue invoice", false, 0)
        );

        return tasks.stream()
                .map(taskSummaryFactory::create)
                .collect(Collectors.toList());
    }
}
