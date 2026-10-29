package com.rest.payment;
public class CashPayment implements PaymentMethod {
    public void pay(double amount) { System.out.printf("Cash payment of Rs. %.2f successful.%n", amount); }
    public String getMethodName() { return "Cash"; }
}
