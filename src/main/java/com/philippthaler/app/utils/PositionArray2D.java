package com.philippthaler.app.utils;

import com.philippthaler.app.Position;
import com.philippthaler.app.exceptions.PositionTakenException;

/**
 * Class that implements the Array2D interface to hold a 2D array of Positions
 */
public class PositionArray2D implements GrowableArray2D<Position> {

  private Position[][] positions;
  private int columns;
  private int rows;

  /**
   * Constructor that specifies the size of the array.
   *
   * @param columns How many columns the array should have.
   * @param rows    How many rows the array should have.
   */
  public PositionArray2D(int columns, int rows) {
    this.columns = columns;
    this.rows = rows;
    positions = new Position[columns][rows];
    initArray();
  }

  /**
   * Adds a new element to the array. It gets placed on the first free space it can find.
   * If the array isn't big enough, more space will be added.
   *
   * @param position
   */
  @Override
  public void add(Position position) {
    if (isFull()) {
      setColumns(columns + 1);
    }
    for (int i = 0; i < columns; i++) {
      for (int j = 0; j < rows; j++) {
        if (isIndexEmpty(i, j)) {
          positions[i][j] = position;
          return;
        }
      }
    }
  }

  @Override
  public void add(Position position, int column, int row) {
    checkIndex(column, row);
    if (!isIndexEmpty(column, row)) {
      throw new PositionTakenException("Position already taken: " + column + "," + row);
    }
    positions[column][row] = (position);
  }

  @Override
  public void set(Position position, int column, int row) {
    checkIndex(column, row);
    positions[column][row] = position;
  }

  @Override
  public void remove(int column, int row) {
    positions[column][row] = new Position();
  }

  @Override
  public Position get(int column, int row) {
    checkIndex(column, row);
    return positions[column][row];
  }

  @Override
  public boolean isIndexEmpty(int column, int row) {
    return positions[column][row].isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    if (o instanceof Position) {
      for (int i = 0; i < columns; i++) {
        for (int j = 0; j < rows; j++) {
          if (positions[i][j].equals(o)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public int getColumns() {
    return columns;
  }

  @Override
  public int getRows() {
    return rows;
  }

  @Override
  public void clear() {
    for (int i = 0; i < columns; i++) {
      for (int j = 0; j < rows; j++) {
        positions[i][j] = new Position();
      }
    }
  }

  @Override
  public Object[] toArray() {
    return positions;
  }

  @Override
  public boolean isFull() {
    for (int i = 0; i < columns; i++) {
      for (int j = 0; j < rows; j++) {
        if (isIndexEmpty(i, j)) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public void setColumns(int columns) {
    setSize(columns, this.rows);
  }

  @Override
  public void setRows(int rows) {
    setSize(this.columns, rows);
  }

  @Override
  public void setSize(int columns, int rows) {
    if (columns == this.columns && rows == this.rows) {
      return;
    }
    Position[][] temp = new Position[columns][rows];

    if (columns > this.columns || rows > this.rows) {
      for (int i = 0; i < this.columns; i++) {
        for (int j = 0; j < this.rows; j++) {
          temp[i][j] = positions[i][j];
        }
      }
    } else if (columns < this.columns || rows < this.rows) {
      for (int i = 0; i < columns; i++) {
        for (int j = 0; j < rows; j++) {
          temp[i][j] = positions[i][j];
        }
      }
    }


    this.columns = columns;
    this.rows = rows;
    positions = temp;
    initArray();
  }

  private void initArray() {
    for (int i = 0; i < columns; i++) {
      for (int j = 0; j < rows; j++) {
        if (positions[i][j] == null) {
          positions[i][j] = new Position();
        }
      }
    }
  }

  // Checks if the index is in range
  private void checkIndex(int column, int row) {
    if (column < 0 || column > columns || row < 0 || row > rows) {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < columns; i++) {
      for (int j = 0; j < rows; j++) {
        sb.append(positions[i][j].isEmpty() ? "_" : "*");
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
