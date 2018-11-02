package com.philippthaler.app.utils;

public class Database2DConfig {
  private int columns;
  private int rows;

  public Database2DConfig(int columns, int rows) {
    this.columns = columns;
    this.rows = rows;
  }

  public int getColumns() {
    return columns;
  }

  public int getRows() {
    return rows;
  }

  public void setColumns(int columns) {
    this.columns = columns;
  }

  public void setRows(int rows) {
    this.rows = rows;
  }
}
