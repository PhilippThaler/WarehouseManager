package com.philippthaler.app.database;

import com.philippthaler.app.Supplier;

import java.util.Map;

public class SupplierDatabase extends Database {

  private Map<String, Supplier> suppliers = createDatabase();

  public SupplierDatabase() {

  }

  Map<String, Supplier> createDatabase() {
    Map<String, Supplier> suppliers = Map.of(
        "grissemann", new Supplier("Grissemann"),
        "wedl", new Supplier("C&C Wedl"),
        "fleischhof", new Supplier("Fleischhof Oberland")
    );

    return suppliers;
  }
}
