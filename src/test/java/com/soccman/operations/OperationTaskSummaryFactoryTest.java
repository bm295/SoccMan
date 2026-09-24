package com.soccman.operations;

import com.soccman.operations.domain.OperationTask;
import com.soccman.operations.domain.TaskCriticalPolicy;
import com.soccman.operations.infrastructure.web.OperationTaskSummaryFactory;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperationTaskSummaryFactoryTest {

    @Test
    void createBuildsSummaryUsingPolicyCriticalThreshold() {
        var factory = new OperationTaskSummaryFactory(new TaskCriticalPolicy());

        Map<String, Object> summary = factory.create(
                new OperationTask("SO-999", "Escalate shipment", false, 3)
        );

        assertEquals("SO-999", summary.get("orderCode"));
        assertEquals("Escalate shipment", summary.get("taskName"));
        assertFalse((Boolean) summary.get("done"));
        assertTrue((Boolean) summary.get("critical"));
    }

    @Test
    void createKeepsTasksAtTwoOverdueDaysNonCritical() {
        var factory = new OperationTaskSummaryFactory(new TaskCriticalPolicy());

        Map<String, Object> summary = factory.create(
                new OperationTask("SO-998", "Confirm address", false, 2)
        );

        assertFalse((Boolean) summary.get("critical"));
    }
}
