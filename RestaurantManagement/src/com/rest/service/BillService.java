package com.rest.service;
import com.rest.models.Bill;
import com.rest.models.Order;
import com.rest.repository.BillRepository;
import java.util.List;

public class BillService {
    private final BillRepository repository;
    private final OrderService orders;
    private int nextBillId = 1;
    private static final double TAX_RATE = 0.05;
    public BillService(BillRepository repository, OrderService orders) { this.repository=repository; this.orders=orders; }
    public Bill generateBill(int orderId) {
        Order order = orders.getById(orderId);
        if (repository.findByOrderId(orderId) != null) throw new IllegalStateException("Bill already exists for this order.");
        Bill bill = new Bill(nextBillId++, order, TAX_RATE);
        order.markBilled(); repository.save(bill); return bill;
    }
    public Bill getById(int id) {
        Bill bill = repository.findById(id);
        if (bill == null) throw new IllegalArgumentException("Bill not found.");
        return bill;
    }
    public Bill getByOrderId(int id) { return repository.findByOrderId(id); }
    public List<Bill> getAll() { return repository.findAll(); }
}
