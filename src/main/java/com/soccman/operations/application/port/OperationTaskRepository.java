package com.soccman.operations.application.port;

import com.soccman.operations.domain.OperationTask;

import java.util.List;

public interface OperationTaskRepository {
    List<OperationTask> findAll();
}

