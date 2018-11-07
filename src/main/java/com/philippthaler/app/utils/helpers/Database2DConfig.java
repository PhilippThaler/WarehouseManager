package com.philippthaler.app.utils.helpers;

/**
 * Class that describes a Position in a two-dimensional array.
 */
public class Database2DConfig {
  private int column;
  private int row;

  /**
   * Initializes this object and checks that the arguments are >= zero
   *
   * @param column
   * @param row
   */
  public Database2DConfig(int column, int row) {
    checkIndices(column, row);
    this.column = column;
    this.row = row;
  }

  public int getColumn() {
    return column;
  }

  public int getRow() {
    return row;
  }

  public void setSize(int column, int row) {
    checkIndices(column, row);
    this.column = column;
    this.row = row;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public void setRow(int row) {
    this.row = row;
  }

  private void checkIndices(int column, int row) {
    if (column < 0 || row < 0) {
      throw new IllegalArgumentException("The column/row size can't be less than zero\nColumn: " + column + "Row: " + row);
    }
  }

  @Override
  public String toString() {
    return "Column: " + column + ", Row: " + row;
  }
}
