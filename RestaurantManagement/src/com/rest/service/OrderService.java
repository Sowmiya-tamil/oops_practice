package com.rest.service;
import com.rest.models.MenuItem;
import com.rest.models.Order;
import com.rest.models.OrderItem;
import com.rest.models.Table;
import com.rest.repository.OrderRepository;
import java.util.List;

public class OrderService {
    private final OrderRepository orders;
    private final TableServices tables;
    private final MenuService menu;
    private int nextOrderId = 1;
    private int nextOrderItemId = 1;
    public OrderService(OrderRepository orders, TableServices tables, MenuService menu) {
        this.orders=orders; this.tables=tables; this.menu=menu;
    }
    public Order createOrder(int tableId) {
        Table table = tables.getById(tableId);
        Order existing = orders.findOpenByTableId(tableId);
        if (existing != null) return existing;
        if (table.getStatus() == Table.Availability.OCCUPIED)
            throw new IllegalStateException("Table is occupied by an order that is not open.");
        table.booking();
        Order order = new Order(nextOrderId++, tableId); orders.save(order); return order;
    }
    public Order addItem(int orderId, int menuItemId, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than zero.");
        Order order = getById(orderId);
        MenuItem item = menu.getById(menuItemId);
        order.addItem(new OrderItem(nextOrderItemId++, item, quantity)); return order;
    }
    public Order getById(int id) {
        Order order = orders.findById(id);
        if (order == null) throw new IllegalArgumentException("Order not found.");
        return order;
    }
    public List<Order> getAll() { return orders.findAll(); }
}
