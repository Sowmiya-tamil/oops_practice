package com.rest.repository;
import com.rest.models.Bill;
import java.util.ArrayList;
import java.util.List;

public class BillRepository {
    private final List<Bill> bills = new ArrayList<>();
    public void save(Bill bill) { bills.add(bill); }
    public List<Bill> findAll() { return new ArrayList<>(bills); }
    public Bill findById(int id) {
        for (Bill bill : bills) if (bill.getId() == id) return bill;
        return null;
    }
    public Bill findByOrderId(int id) {
        for (Bill bill : bills) if (bill.getOrderId() == id) return bill;
        return null;
    }
}
