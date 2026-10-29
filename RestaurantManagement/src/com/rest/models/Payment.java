package com.rest.models;

public class Payment {
    private final int id;
    private final double amount;
    private final String method;
    private final String status;
    private final int billId;

    public Payment(int id, double amount, String method, String status) {
        this(id, amount, method, status, 0);
    }
    public Payment(int id, double amount, String method, String status, int billId) {
        this.id=id; this.amount=amount; this.method=method; this.status=status; this.billId=billId;
    }
    public int getId() { return id; }
    public double getAmount() { return amount; }
    public String getMethod() { return method; }
    public String getStatus() { return status; }
    public int getBillId() { return billId; }
    @Override public String toString() {
        return String.format("Payment ID: %d | Bill ID: %d | Amount: Rs. %.2f | Method: %s | Status: %s",
                id, billId, amount, method, status);
    }
}
