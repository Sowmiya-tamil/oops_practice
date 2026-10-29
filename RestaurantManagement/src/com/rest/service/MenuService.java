package com.rest.service;
import com.rest.models.MenuItem;
import com.rest.repository.MenuRepository;
import java.util.List;

public class MenuService {
    private final MenuRepository repository = new MenuRepository();
    public List<MenuItem> getMenu() { return repository.findAvailable(); }
    public List<MenuItem> getAll() { return repository.findAll(); }
    public MenuItem getById(int id) {
        MenuItem item = repository.findById(id);
        if (item == null || !item.isAvailable()) throw new IllegalArgumentException("Menu item not found or unavailable.");
        return item;
    }
}
