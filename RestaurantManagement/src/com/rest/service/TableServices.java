package com.rest.service;
import com.rest.models.Table;
import com.rest.repository.TableRepository;
import java.util.List;

public class TableServices {
    private final TableRepository repository;
    public TableServices() { this(new TableRepository()); }
    public TableServices(TableRepository repository) { this.repository = repository; }
    public List<Table> getAll() { return repository.findAll(); }
    public Table getById(int id) {
        Table table = repository.findById(id);
        if (table == null) throw new IllegalArgumentException("Table not found.");
        return table;
    }
    public Table occupy(int id) {
        Table table = getById(id);
        if (table.getStatus() == Table.Availability.OCCUPIED) throw new IllegalStateException("Table is already occupied.");
        table.booking(); return table;
    }
    public void release(int id) { getById(id).freeNow(); }
}
