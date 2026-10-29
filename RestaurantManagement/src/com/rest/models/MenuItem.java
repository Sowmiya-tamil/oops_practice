package com.rest.models;

public class MenuItem {
    private final int id;
    private final String name;
    private final String category;
    private final double price;
    private boolean available;

    public MenuItem(int id, String name, String category, double price) {
        if (id <= 0 || name == null || name.trim().isEmpty() || price < 0)
            throw new IllegalArgumentException("Invalid menu item details.");
        this.id = id; this.name = name.trim(); this.category = category;
        this.price = price; this.available = true;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    @Override public String toString() {
        return String.format("%d. %-20s | %-10s | Rs. %.2f%s", id, name, category, price,
                available ? "" : " | UNAVAILABLE");
    }
}
