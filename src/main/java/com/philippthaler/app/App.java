package com.philippthaler.app;

import com.philippthaler.app.utils.Warehouse;

/**
 * Hello world!
 */
public class App {

  public static final int COLUMN_INIT=10, ROW_INIT = 10;

  public static void main(String[] args) {
    Warehouse warehouse = Warehouse.getInstance();

    warehouse.startUI();

  }
}
