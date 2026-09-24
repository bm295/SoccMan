package com.soccman.operations.infrastructure.web;

import com.soccman.operations.application.ListOperationTasksUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/operations")
public class OperationsController {
    private final OperationTaskSummaryFactory taskSummaryFactory;
    private final ListOperationTasksUseCase listTasks;

    public OperationsController(
            OperationTaskSummaryFactory taskSummaryFactory,
            ListOperationTasksUseCase listTasks
    ) {
        this.taskSummaryFactory = taskSummaryFactory;
        this.listTasks = listTasks;
    }

    @GetMapping("/tasks")
    public List<Map<String, Object>> tasks() {
        return listTasks.execute().stream()
                .map(taskSummaryFactory::create)
                .toList();
    }
}

