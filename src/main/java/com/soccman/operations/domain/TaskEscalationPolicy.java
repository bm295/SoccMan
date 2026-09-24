package com.soccman.operations.domain;

import java.util.List;
import java.util.Optional;

public final class TaskEscalationPolicy {

    public Optional<MostOverdueTask> findMostOverdue(
            List<OperationTask> tasks,
            long escalationThresholdDays
    ) {
        if (escalationThresholdDays < 0) {
            throw new IllegalArgumentException("Escalation threshold must not be negative");
        }

        OperationTask mostOverdueTask = null;
        long maxOverdueDays = 0;

        for (OperationTask task : tasks) {
            if (task.isOverdue() && task.overdueDays() > maxOverdueDays) {
                maxOverdueDays = task.overdueDays();
                mostOverdueTask = task;
            }
        }

        if (mostOverdueTask == null) {
            return Optional.empty();
        }

        return Optional.of(new MostOverdueTask(
                mostOverdueTask.orderCode(),
                mostOverdueTask.taskName(),
                maxOverdueDays,
                Math.max(0, maxOverdueDays - escalationThresholdDays)
        ));
    }
}
