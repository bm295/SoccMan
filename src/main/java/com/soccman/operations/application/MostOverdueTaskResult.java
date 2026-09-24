package com.soccman.operations.application;

public record MostOverdueTaskResult(
        String orderCode,
        String taskName,
        long overdueDays,
        long daysBeyondEscalationThreshold
) {
}

