package com.soccman.cashflow;

public record CashflowSnapshot(long expectedIncoming, long expectedOutgoing, long overdueReceivables, long upcomingPayables) {
}
