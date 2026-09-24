package com.soccman.operations.domain;

public final class TaskCriticalPolicy {

    public boolean isCritical(long overdueDays) {
        return overdueDays > 2;
    }
}

