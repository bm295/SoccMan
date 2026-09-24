package com.soccman.operations.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskEscalationPolicyTest {

    private final TaskEscalationPolicy policy = new TaskEscalationPolicy();

    @Test
    void findsMostOverdueIncompleteTaskAndCalculatesThresholdExcess() {
        var tasks = List.of(
                new OperationTask("SO-001", "Call customer", true, 7),
                new OperationTask("SO-001", "Prepare package", false, 3),
                new OperationTask("SO-001", "Confirm delivery", false, 1)
        );

        MostOverdueTask result = policy.findMostOverdue(tasks, 1).orElseThrow();

        assertEquals("Prepare package", result.taskName());
        assertEquals(3, result.overdueDays());
        assertEquals(2, result.daysBeyondEscalationThreshold());
    }

    @Test
    void returnsEmptyWhenThereAreNoIncompleteOverdueTasks() {
        var tasks = List.of(
                new OperationTask("SO-001", "Completed late task", true, 5),
                new OperationTask("SO-002", "Upcoming task", false, 0)
        );

        assertTrue(policy.findMostOverdue(tasks, 1).isEmpty());
    }

    @Test
    void rejectsNegativeEscalationThreshold() {
        assertThrows(
                IllegalArgumentException.class,
                () -> policy.findMostOverdue(List.of(), -1)
        );
    }
}
