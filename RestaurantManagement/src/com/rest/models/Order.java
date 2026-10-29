package com.rest.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    public enum Status { OPEN, BILLED, PAID }
    private final int id;
    private final int tableId;
    private final List<OrderItem> items = new ArrayList<>();
    private Status status = Status.OPEN;

    public Order(int id, int tableId) { this.id = id; this.tableId = tableId; }
    public int getId() { return id; }
    public int getTableId() { return tableId; }
    public List<OrderItem> getItems() { return Collections.unmodifiableList(items); }
    public Status getStatus() { return status; }
    public void addItem(OrderItem item) {
        if (status != Status.OPEN) throw new IllegalStateException("Only open orders can be changed.");
        items.add(item);
    }
    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) total += item.getSubtotal();
        return total;
    }
    public void markBilled() {
        if (items.isEmpty()) throw new IllegalStateException("Cannot bill an empty order.");
        status = Status.BILLED;
    }
    public void markPaid() { status = Status.PAID; }
    @Override public String toString() {
        return String.format("Order %d | Table %d | Items: %d | Total: Rs. %.2f | %s",
                id, tableId, items.size(), getTotal(), status);
    }
}
