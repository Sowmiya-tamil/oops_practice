package com.rest.CLI;
import com.rest.models.MenuItem;
import com.rest.service.MenuService;
import java.util.Scanner;

public class MenuCli {
    private final MenuService service;
    private final Scanner scanner;
    public MenuCli(MenuService service, Scanner scanner) { this.service = service; this.scanner = scanner; }
    public void start() {
        System.out.println("\\n--- MENU ITEMS ---");
        for (MenuItem item : service.getMenu()) System.out.println(item);
    }
}
