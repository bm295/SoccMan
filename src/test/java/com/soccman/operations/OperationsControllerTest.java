package com.soccman.operations;

import com.soccman.common.PolicyService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperationsControllerTest {

    @Test
    void tasksReturnsTaskSummariesWithCriticalFlagsFromPolicy() {
        var controller = new OperationsController(new OperationTaskSummaryFactory(new PolicyService()));

        List<Map<String, Object>> tasks = controller.tasks();

        assertEquals(4, tasks.size());

        assertEquals("SO-001", tasks.getFirst().get("orderCode"));
        assertEquals("Call customer", tasks.getFirst().get("taskName"));
        assertTrue((Boolean) tasks.getFirst().get("done"));
        assertFalse((Boolean) tasks.getFirst().get("critical"));

        assertEquals("Prepare package", tasks.get(1).get("taskName"));
        assertFalse((Boolean) tasks.get(1).get("done"));
        assertTrue((Boolean) tasks.get(1).get("critical"));

        assertEquals("Confirm delivery", tasks.get(2).get("taskName"));
        assertFalse((Boolean) tasks.get(2).get("critical"));
    }
}
