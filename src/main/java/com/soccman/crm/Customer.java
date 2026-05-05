package com.soccman.crm;

public record Customer(String id, String name, String company, String stage, boolean overdueReceivable) {
}
