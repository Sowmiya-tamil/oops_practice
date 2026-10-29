package com.rest.CLI;
import com.rest.models.Bill;
import com.rest.service.BillService;
import java.util.List;
import java.util.Scanner;

public class BillCli {
    private final BillService service;
    private final Scanner scanner;
    public BillCli(BillService service, Scanner scanner) { this.service = service; this.scanner = scanner; }
    public void start() {
        System.out.println("\\n1. Generate bill\\n2. View all bills");
        int choice = readInt("Choose: ");
        if (choice == 1) {
            int orderId = readInt("Enter order ID: ");
            Bill bill = service.generateBill(orderId);
            System.out.println("\\n--- BILL ---\\n" + bill);
        } else if (choice == 2) {
            List<Bill> bills = service.getAll();
            if (bills.isEmpty()) System.out.println("No bills found.");
            for (Bill bill : bills) System.out.println("\\n" + bill);
        } else System.out.println("Invalid choice.");
    }
    private int readInt(String prompt) {
        while (true) { System.out.print(prompt); try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Enter a valid whole number."); } }
    }
}
