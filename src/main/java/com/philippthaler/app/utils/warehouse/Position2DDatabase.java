package com.philippthaler.app.utils.warehouse;

import com.philippthaler.app.model.Position;
import com.philippthaler.app.exceptions.PositionTakenException;
import com.philippthaler.app.utils.helpers.Database2DConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements the Array2D interface to hold a 2D array of Positions
 */
public class Position2DDatabase implements GrowableArray2D<Position> {

  private Position[][] positions;
  private Database2DConfig size;

  /**
   * Constructor that specifies the size of the array.
   *
   * @param columns How many columns the array should have.
   * @param rows    How many rows the array should have.
   */
  public Position2DDatabase(int columns, int rows) {
    size = new Database2DConfig(columns, rows);
    positions = new Position[columns][rows];
    initArray();
  }

  /**
   * Adds a new element to the array. It gets placed on the first free space it can find.
   * If the array isn't big enough, more space will be added.
   *
   * @param position The position which will get added.
   */
  @Override
  public void add(Position position) {
    if (isFull()) {
      setColumns(size.getColumn() + 1);
    }
    for (int i = 0; i < size.getColumn(); i++) {
      for (int j = 0; j < size.getRow(); j++) {
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
    set(position, new Database2DConfig(column, row));
  }

  @Override
  public void set(Position position, Database2DConfig config) {
    int column = config.getColumn();
    int row = config.getRow();
    checkIndex(column, row);
    positions[column][row] = position;
  }

  @Override
  public void remove(int column, int row) {
    positions[column][row] = new Position(column, row);
  }

  @Override
  public Position get(int column, int row) {
    return get(new Database2DConfig(column, row));
  }

  @Override
  public Position get(Database2DConfig config) {
    int column = config.getColumn();
    int row = config.getRow();
    checkIndex(column, row);
    return positions[column][row];
  }

  @Override
  public boolean isIndexEmpty(int column, int row) {
    return positions[column][row].isEmpty();
  }

  public boolean isIndexFull(int column, int row, String articleName) {
    if (positions[column][row].getArticle().getName().toLowerCase().equals(articleName.toLowerCase()) && !positions[column][row].isFull()) {
      return false;
    } else return !isIndexEmpty(column, row);
  }

  @Override
  public boolean contains(Object o) {
    if (o instanceof Position) {
      for (int i = 0; i < size.getColumn(); i++) {
        for (int j = 0; j < size.getRow(); j++) {
          if (positions[i][j].equals(o)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public Database2DConfig getNextFreePosition(String articleName) {
    for (int i = 0; i < size.getColumn(); i++) {
      for (int j = 0; j < size.getRow(); j++) {
        if (isIndexEmpty(i, j) || !isIndexFull(i, j, articleName)) {
          return new Database2DConfig(i, j);
        }
      }
    }
    return null;
  }

  @Override
  public List<Database2DConfig> getPositions(String articleName) {
    List<Database2DConfig> positionList = new ArrayList<>();
    for (int i = 0; i < size.getColumn(); i++) {
      for (int j = 0; j < size.getRow(); j++) {
        if (positions[i][j].getArticle() != null) {
          if (positions[i][j].getArticle().getName().toLowerCase().equals(articleName.toLowerCase())) {
            positionList.add(positions[i][j].getArrayPosition());
          }
        }
      }
    }
    return positionList;
  }

  @Override
  public int getColumns() {
    return size.getColumn();
  }

  @Override
  public int getRows() {
    return size.getRow();
  }

  @Override
  public void clear() {
    for (int i = 0; i < size.getColumn(); i++) {
      for (int j = 0; j < size.getRow(); j++) {
        positions[i][j] = new Position(i, j);
      }
    }
  }

  @Override
  public Position[][] toArray() {
    return positions;
  }

  @Override
  public boolean isFull() {
    for (int i = 0; i < size.getColumn(); i++) {
      for (int j = 0; j < size.getRow(); j++) {
        if (isIndexEmpty(i, j)) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public Position[] getArrayOfNonEmptyPositions() {
    Position[] nonEmpty = new Position[getNumberOfNonEmptyPositions()];
    int count = 0;
    for (int i = 0; i < size.getColumn(); i++) {
      for (int j = 0; j < size.getRow(); j++) {
        if (!positions[i][j].isEmpty()) {
          nonEmpty[count] = positions[i][j];
          count++;
        }
      }
    }
    return nonEmpty;
  }

  public int getNumberOfNonEmptyPositions() {
    int count = 0;
    for (int i = 0; i < size.getColumn(); i++) {
      for (int j = 0; j < size.getRow(); j++) {
        if (!positions[i][j].isEmpty()) {
          count++;
        }
      }
    }
    return count;
  }

  @Override
  public void setColumns(int columns) {
    setSize(columns, size.getRow());
  }

  @Override
  public void setRows(int rows) {
    setSize(size.getColumn(), rows);
  }

  @Override
  public void setSize(int columns, int rows) {
    if (columns == size.getColumn() && rows == size.getRow()) {
      return;
    }
    Position[][] temp = new Position[columns][rows];

    if (columns > size.getColumn() || rows > size.getRow()) {
      for (int i = 0; i < size.getColumn(); i++) {
        if (size.getRow() >= 0) System.arraycopy(positions[i], 0, temp[i], 0, size.getRow());
      }
    } else if (columns < size.getColumn() || rows < size.getRow()) {
      for (int i = 0; i < columns; i++) {
        if (rows >= 0) System.arraycopy(positions[i], 0, temp[i], 0, rows);
      }
    }

    size.setColumn(columns);
    size.setRow(rows);
    positions = temp;
    initArray();
  }

  @Override
  public void setSize(Database2DConfig config) {
    setSize(config.getColumn(), config.getRow());
  }

  private void initArray() {
    for (int i = 0; i < size.getColumn(); i++) {
      for (int j = 0; j < size.getRow(); j++) {
        if (positions[i][j] == null) {
          positions[i][j] = new Position(i, j);
        }
      }
    }
  }

  // Checks if the index is in range
  private void checkIndex(int column, int row) {
    if (column < 0 || column > size.getColumn() || row < 0 || row > size.getRow()) {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < size.getColumn(); i++) {
      for (int j = 0; j < size.getRow(); j++) {
        sb.append(positions[i][j].isEmpty() ? "_" : "*");
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
