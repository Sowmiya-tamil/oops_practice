package com.rest.CLI;
import com.rest.models.Order;
import com.rest.models.OrderItem;
import com.rest.models.Table;
import com.rest.service.OrderService;
import com.rest.service.TableServices;
import java.util.List;
import java.util.Scanner;

public class OrderCli {
    private final OrderService service;
    private final TableServices tables;
    private final Scanner scanner;
    public OrderCli(OrderService service, TableServices tables, Scanner scanner) {
        this.service = service; this.tables = tables; this.scanner = scanner;
    }
    public void start() {
        System.out.println("\\n1. Create order for table\\n2. View all orders");
        int choice = readInt("Choose: ");
        if (choice == 1) {
            for (Table table : tables.getAll()) System.out.println(table);
            int tableId = readInt("Enter table ID: ");
            Order order = service.createOrder(tableId);
            System.out.println("Order created/opened: " + order);
        } else if (choice == 2) {
            List<Order> orders = service.getAll();
            if (orders.isEmpty()) System.out.println("No orders found.");
            for (Order order : orders) {
                System.out.println(order);
                for (OrderItem item : order.getItems()) System.out.println("  " + item);
            }
        } else System.out.println("Invalid choice.");
    }
    private int readInt(String prompt) {
        while (true) { System.out.print(prompt); try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Enter a valid whole number."); } }
    }
}
