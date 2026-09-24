package com.soccman.operations.application;

import com.soccman.operations.application.port.OperationTaskRepository;
import com.soccman.operations.domain.OperationTask;

import java.util.List;

public final class ListOperationTasksService implements ListOperationTasksUseCase {
    private final OperationTaskRepository taskRepository;

    public ListOperationTasksService(OperationTaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<OperationTask> execute() {
        return taskRepository.findAll();
    }
}

