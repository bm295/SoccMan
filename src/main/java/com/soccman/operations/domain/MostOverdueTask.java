package com.soccman.operations.domain;

public record MostOverdueTask(
        String orderCode,
        String taskName,
        long overdueDays,
        long daysBeyondEscalationThreshold
) {
}

