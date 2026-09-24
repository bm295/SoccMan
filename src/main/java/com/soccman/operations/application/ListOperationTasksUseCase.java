package com.soccman.operations.application;

import com.soccman.operations.domain.OperationTask;

import java.util.List;

public interface ListOperationTasksUseCase {
    List<OperationTask> execute();
}

