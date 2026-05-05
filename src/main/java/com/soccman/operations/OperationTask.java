package com.soccman.operations;

public record OperationTask(String orderCode, String taskName, boolean done, long overdueDays) {
}
