package com.rest.repository;
import com.rest.models.Table;
import java.util.ArrayList;
import java.util.List;

public class TableRepository {
    private final List<Table> tables = new ArrayList<>();
    public TableRepository() {
        tables.add(new Table(1, 5)); tables.add(new Table(2, 2)); tables.add(new Table(3, 7));
        tables.add(new Table(4, 4)); tables.add(new Table(5, 6));
    }
    public List<Table> findAll() { return new ArrayList<>(tables); }
    public Table findById(int id) {
        for (Table t : tables) if (t.getId() == id) return t;
        return null;
    }
}
