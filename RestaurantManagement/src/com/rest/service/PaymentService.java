package com.rest.service;
import com.rest.models.Bill;
import com.rest.models.Order;
import com.rest.models.Payment;
import com.rest.payment.PaymentMethod;
import com.rest.repository.PaymentRepository;
import java.util.List;

public class PaymentService {
    private final PaymentRepository repository;
    private final BillService bills;
    private final OrderService orders;
    private final TableServices tables;
    private int nextPaymentId = 1;
    public PaymentService() {
        this.repository = new PaymentRepository(); this.bills = null; this.orders = null; this.tables = null;
    }
    public PaymentService(PaymentRepository repository, BillService bills, OrderService orders, TableServices tables) {
        this.repository=repository; this.bills=bills; this.orders=orders; this.tables=tables;
    }
    public Payment processPayment(PaymentMethod method, double amount) {
        if (method == null) throw new IllegalArgumentException("Payment method is required.");
        if (amount <= 0) throw new IllegalArgumentException("Amount must be greater than zero.");
        method.pay(amount);
        Payment payment = new Payment(nextPaymentId++, amount, method.getMethodName(), "SUCCESS");
        repository.save(payment); return payment;
    }
    public Payment payBill(int billId, PaymentMethod method) {
        if (bills == null) throw new IllegalStateException("Bill payment service is not configured.");
        Bill bill = bills.getById(billId);
        Order order = orders.getById(bill.getOrderId());
        if (order.getStatus() == Order.Status.PAID) throw new IllegalStateException("Bill is already paid.");
        method.pay(bill.getTotal());
        Payment payment = new Payment(nextPaymentId++, bill.getTotal(), method.getMethodName(), "SUCCESS", billId);
        repository.save(payment); order.markPaid(); tables.release(bill.getTableId()); return payment;
    }
    public List<Payment> getAllPayments() { return repository.findAll(); }
    public Payment getPaymentById(int id) {
        Payment payment=repository.findById(id);
        if (payment == null) throw new IllegalArgumentException("Payment not found.");
        return payment;
    }
}
