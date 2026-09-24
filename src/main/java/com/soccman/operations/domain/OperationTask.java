package com.soccman.operations.domain;

public record OperationTask(String orderCode, String taskName, boolean done, long overdueDays) {

    public boolean isOverdue() {
        return !done && overdueDays > 0;
    }
}

