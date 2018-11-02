package com.philippthaler.app;

import com.philippthaler.app.logic.Warehouse;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) {
    Warehouse warehouse = new Warehouse(10, 10);

    warehouse.startUI();

  }
}
