package com.philippthaler.app.utils;

public class Database2DConfig {
  private int column;
  private int row;

  public Database2DConfig(int column, int row) {
    this.column = column;
    this.row = row;
  }

  public int getColumn() {
    return column;
  }

  public int getRow() {
    return row;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public void setRow(int row) {
    this.row = row;
  }

  @Override
  public String toString() {
    return "Column: " + column + ", Row: " + row;
  }
}
