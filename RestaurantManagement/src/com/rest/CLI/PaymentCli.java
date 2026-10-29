package com.rest.CLI;
import com.rest.models.Payment;
import com.rest.payment.*;
import com.rest.service.PaymentService;
import java.util.Scanner;
/** Legacy standalone payment CLI; the full application uses RestaurantCli. */
public class PaymentCli {
    private final PaymentService service = new PaymentService();
    private final Scanner scanner = new Scanner(System.in);
    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== PAYMENT SYSTEM ===\n1. Make payment\n2. View payments\n3. Find payment\n0. Exit");
            int choice = readInt("Enter choice: ");
            try {
                switch (choice) {
                    case 1:
                        double amount = readDouble("Enter amount: Rs. ");
                        System.out.println("1. Cash  2. Card  3. UPI");
                        int type = readInt("Choose method: ");
                        PaymentMethod method = type == 1 ? new CashPayment() : type == 2 ? new CardPayment() : type == 3 ? new UPIPayment() : null;
                        if (method == null) throw new IllegalArgumentException("Invalid payment method.");
                        System.out.println(service.processPayment(method, amount)); break;
                    case 2: service.getAllPayments().forEach(System.out::println); break;
                    case 3: System.out.println(service.getPaymentById(readInt("Payment ID: "))); break;
                    case 0: running = false; break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException ex) { System.out.println("Error: " + ex.getMessage()); }
        }
    }
    private int readInt(String prompt) {
        while (true) { System.out.print(prompt); try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException ex) { System.out.println("Enter a valid whole number."); } }
    }
    private double readDouble(String prompt) {
        while (true) { System.out.print(prompt); try { return Double.parseDouble(scanner.nextLine().trim()); }
            catch (NumberFormatException ex) { System.out.println("Enter a valid amount."); } }
    }
}
