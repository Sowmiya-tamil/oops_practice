package com.rest.CLI;
import com.rest.models.Order;
import com.rest.models.OrderItem;
import com.rest.service.OrderService;
import java.util.Scanner;

public class OrderItemCli {
    private final OrderService service;
    private final Scanner scanner;
    public OrderItemCli(OrderService service, Scanner scanner) { this.service = service; this.scanner = scanner; }
    public void start() {
        int orderId = readInt("Enter order ID: ");
        Order order = service.getById(orderId);
        if (order.getStatus() != Order.Status.OPEN) {
            System.out.println("Cannot add items: order is " + order.getStatus());
            return;
        }
        int menuId = readInt("Enter menu item ID: ");
        int quantity = readInt("Enter quantity: ");
        Order updated = service.addItem(orderId, menuId, quantity);
        System.out.println("Order item added successfully.");
        for (OrderItem item : updated.getItems()) System.out.println(item);
        System.out.printf("Order total: Rs. %.2f%n", updated.getTotal());
    }
    private int readInt(String prompt) {
        while (true) { System.out.print(prompt); try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Enter a valid whole number."); } }
    }
}
