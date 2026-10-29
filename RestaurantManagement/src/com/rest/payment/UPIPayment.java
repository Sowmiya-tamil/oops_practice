package com.rest.payment;
public class UPIPayment implements PaymentMethod {
    public void pay(double amount) { System.out.printf("UPI payment of Rs. %.2f successful.%n", amount); }
    public String getMethodName() { return "UPI"; }
}
