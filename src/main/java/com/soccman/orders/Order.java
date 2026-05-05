package com.soccman.orders;

public record Order(String orderCode, String customerId, long amount, OrderStatus status) {
}
