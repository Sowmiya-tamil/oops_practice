package com.rest.repository;
import com.rest.models.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository {
    private final List<MenuItem> items = new ArrayList<>();
    public MenuRepository() {
        items.add(new MenuItem(1, "Idli", "Breakfast", 40));
        items.add(new MenuItem(2, "Dosa", "Breakfast", 70));
        items.add(new MenuItem(3, "Veg Meals", "Meals", 120));
        items.add(new MenuItem(4, "Paneer Biryani", "Main Course", 180));
        items.add(new MenuItem(5, "Chicken Biryani", "Main Course", 220));
        items.add(new MenuItem(6, "Fresh Lime Soda", "Drinks", 50));
        items.add(new MenuItem(7, "Gulab Jamun", "Dessert", 60));
    }
    public List<MenuItem> findAll() { return new ArrayList<>(items); }
    public List<MenuItem> findAvailable() {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : items) if (item.isAvailable()) result.add(item);
        return result;
    }
    public MenuItem findById(int id) {
        for (MenuItem item : items) if (item.getId() == id) return item;
        return null;
    }
}
