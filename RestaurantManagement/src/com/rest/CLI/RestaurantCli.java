package com.rest.CLI;

import com.rest.models.Payment;
import com.rest.payment.*;
import com.rest.repository.*;
import com.rest.service.*;
import java.util.Scanner;

public class RestaurantCli {
    private final Scanner scanner = new Scanner(System.in);
    private final TableServices tableService = new TableServices();
    private final MenuService menuService = new MenuService();
    private final OrderService orderService = new OrderService(new OrderRepository(), tableService, menuService);
    private final BillService billService = new BillService(new BillRepository(), orderService);
    private final PaymentService paymentService = new PaymentService(new PaymentRepository(), billService, orderService, tableService);
    private final TableCli tableCli = new TableCli(tableService);
    private final MenuCli menuCli = new MenuCli(menuService, scanner);
    private final OrderCli orderCli = new OrderCli(orderService, tableService, scanner);
    private final OrderItemCli orderItemCli = new OrderItemCli(orderService, scanner);
    private final BillCli billCli = new BillCli(billService, scanner);

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\\n====== RESTAURANT MANAGEMENT SYSTEM ======");
            System.out.println("1. Tables");
            System.out.println("2. Menu CLI");
            System.out.println("3. Order CLI (create/view orders)");
            System.out.println("4. Order Items CLI (add items to an order)");
            System.out.println("5. Bill CLI (generate/view bills)");
            System.out.println("6. Pay bill");
            System.out.println("7. View payments");
            System.out.println("0. Exit");
            int choice = readInt("Enter choice: ");
            try {
                switch (choice) {
                    case 1: tableCli.start(); break;
                    case 2: menuCli.start(); break;
                    case 3: orderCli.start(); break;
                    case 4: orderItemCli.start(); break;
                    case 5: billCli.start(); break;
                    case 6: payBill(); break;
                    case 7: paymentService.getAllPayments().forEach(System.out::println); break;
                    case 0: running = false; System.out.println("Thank you!"); break;
                    default: System.out.println("Please choose a valid menu option.");
                }
            } catch (IllegalArgumentException | IllegalStateException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        scanner.close();
    }
    private void payBill() {
        int billId = readInt("Enter bill ID: ");
        System.out.println("1. Cash  2. Card  3. UPI");
        int choice = readInt("Choose payment method: ");
        PaymentMethod method;
        switch (choice) {
            case 1: method = new CashPayment(); break;
            case 2: method = new CardPayment(); break;
            case 3: method = new UPIPayment(); break;
            default: throw new IllegalArgumentException("Invalid payment method.");
        }
        Payment payment = paymentService.payBill(billId, method);
        System.out.println("Payment saved: " + payment);
    }
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try { return Integer.parseInt(input); }
            catch (NumberFormatException ex) { System.out.println("Enter a valid whole number."); }
        }
    }
}
