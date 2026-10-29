package com.rest.models;

public class Table {
    public enum Availability { FREE, OCCUPIED }
    private final int id;
    private final int capacity;
    private Availability status;

    public Table(int id, int capacity) {
        if (id <= 0 || capacity <= 0) throw new IllegalArgumentException("Table ID and capacity must be positive.");
        this.id = id;
        this.capacity = capacity;
        this.status = Availability.FREE;
    }
    public int getId() { return id; }
    public int getCapacity() { return capacity; }
    // Backward-compatible spelling from the starter project.
    public int getCapasity() { return capacity; }
    public Availability getStatus() { return status; }
    public void booking() { status = Availability.OCCUPIED; }
    public void freeNow() { status = Availability.FREE; }
    @Override public String toString() {
        return "Table " + id + " | Capacity: " + capacity + " | Status: " + status;
    }
}
