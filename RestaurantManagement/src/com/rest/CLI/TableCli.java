package com.rest.CLI;
import com.rest.service.TableServices;
public class TableCli {
    private final TableServices service;
    public TableCli(TableServices service) { this.service = service; }
    public void start() { service.getAll().forEach(System.out::println); }
}
