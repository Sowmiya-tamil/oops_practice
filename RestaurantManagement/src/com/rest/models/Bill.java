package com.rest.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bill {
    private final int id;
    private final int orderId;
    private final int tableId;
    private final double subtotal;
    private final double tax;
    private final double total;
    private final LocalDateTime createdAt;

    public Bill(int id, Order order, double taxRate) {
        this.id = id; this.orderId = order.getId(); this.tableId = order.getTableId();
        this.subtotal = order.getTotal(); this.tax = subtotal * taxRate;
        this.total = subtotal + tax; this.createdAt = LocalDateTime.now();
    }
    public int getId() { return id; }
    public int getOrderId() { return orderId; }
    public int getTableId() { return tableId; }
    public double getSubtotal() { return subtotal; }
    public double getTax() { return tax; }
    public double getTotal() { return total; }
    @Override public String toString() {
        return String.format("Bill #%d | Order #%d | Table %d%nSubtotal: Rs. %.2f%nTax: Rs. %.2f%nTOTAL: Rs. %.2f%nCreated: %s",
                id, orderId, tableId, subtotal, tax, total,
                createdAt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }
}
