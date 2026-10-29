# Restaurant Management System (Java CLI)

Terminal-only project. No GUI or database; repositories store data in memory while the program runs.

## Layers
- `src/com/rest/CLI`: `TableCli`, `MenuCli`, `OrderCli`, `OrderItemCli`, `BillCli`, `PaymentCli`, `RestaurantCli`
- `src/com/rest/models`: Table, MenuItem, Order, OrderItem, Bill, Payment
- `src/com/rest/repository`: in-memory repositories
- `src/com/rest/service`: business rules
- `src/com/rest/payment`: `PaymentMethod` interface and Cash/Card/UPI implementations
- `Main.java`: application entry point

## Compile and run
From the project root:

```bash
javac -d out Main.java src/com/rest/models/*.java src/com/rest/payment/*.java src/com/rest/repository/*.java src/com/rest/service/*.java src/com/rest/CLI/*.java
java -cp out Main
```

## Main menu
1. Tables
2. Menu CLI
3. Order CLI (create/view orders)
4. Order Items CLI (add items to an order)
5. Bill CLI (generate/view bills)
6. Pay bill
7. View payments
0. Exit

Bill tax is currently set to 5% in `BillService`. Data resets when the application exits because repositories are in-memory.
