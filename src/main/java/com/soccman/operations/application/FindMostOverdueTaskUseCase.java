package com.soccman.operations.application;

import java.util.Optional;

public interface FindMostOverdueTaskUseCase {
    Optional<MostOverdueTaskResult> execute(long escalationThresholdDays);
}

