package com.soccman.operations.application;

import com.soccman.operations.application.port.OperationTaskRepository;
import com.soccman.operations.domain.OperationTask;
import com.soccman.operations.domain.TaskEscalationPolicy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindMostOverdueTaskServiceTest {

    @Test
    void readsTasksThroughRepositoryPortAndAppliesDomainPolicy() {
        OperationTaskRepository repository = () -> List.of(
                new OperationTask("SO-100", "Invoice customer", false, 4),
                new OperationTask("SO-101", "Book delivery", false, 2)
        );
        var service = new FindMostOverdueTaskService(
                repository,
                new TaskEscalationPolicy()
        );

        MostOverdueTaskResult result = service.execute(1).orElseThrow();

        assertEquals("SO-100", result.orderCode());
        assertEquals("Invoice customer", result.taskName());
        assertEquals(3, result.daysBeyondEscalationThreshold());
    }
}
