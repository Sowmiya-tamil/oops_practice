package com.rest.models;

public class OrderItem {
    private final int id;
    private final int menuItemId;
    private final String itemName;
    private final double unitPrice;
    private final int quantity;

    public OrderItem(int id, MenuItem menuItem, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than zero.");
        this.id = id; this.menuItemId = menuItem.getId(); this.itemName = menuItem.getName();
        this.unitPrice = menuItem.getPrice(); this.quantity = quantity;
    }
    public int getId() { return id; }
    public int getMenuItemId() { return menuItemId; }
    public String getItemName() { return itemName; }
    public double getUnitPrice() { return unitPrice; }
    public int getQuantity() { return quantity; }
    public double getSubtotal() { return unitPrice * quantity; }
    @Override public String toString() {
        return String.format("OrderItem %d | %s x %d | Rs. %.2f", id, itemName, quantity, getSubtotal());
    }
}
