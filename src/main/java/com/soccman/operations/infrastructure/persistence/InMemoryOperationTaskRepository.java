package com.soccman.operations.infrastructure.persistence;

import com.soccman.operations.application.port.OperationTaskRepository;
import com.soccman.operations.domain.OperationTask;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryOperationTaskRepository implements OperationTaskRepository {
    private final List<OperationTask> tasks = List.of(
            new OperationTask("SO-001", "Call customer", true, 0),
            new OperationTask("SO-001", "Prepare package", false, 3),
            new OperationTask("SO-001", "Confirm delivery", false, 1),
            new OperationTask("SO-001", "Issue invoice", false, 0)
    );

    @Override
    public List<OperationTask> findAll() {
        return tasks;
    }
}

