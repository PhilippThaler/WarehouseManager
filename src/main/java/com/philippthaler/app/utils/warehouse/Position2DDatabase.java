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

  /**
   * Method for adding a Position object in the specified 2D-array position.
   * Throws an Exception when the position is already taken.
   *
   * @param position The position which will get added
   * @param column   The column
   * @param row      The row
   */
  @Override
  public void add(Position position, int column, int row) {
    checkIndex(column, row);
    if (!isIndexEmpty(column, row)) {
      throw new PositionTakenException("Position already taken: " + column + "," + row);
    }
    positions[column][row] = (position);
  }

  /**
   * Sets the position specified to a new position.
   *
   * @param position The new Position object
   * @param column   The column in the 2D-array
   * @param row      The row in the 2D-array
   */
  @Override
  public void set(Position position, int column, int row) {
    set(position, new Database2DConfig(column, row));
  }

  /**
   * Sets the position specified to a new position.
   *
   * @param position The new Position object
   * @param config   An object, that holds informations about the position in the 2D-array.
   */
  @Override
  public void set(Position position, Database2DConfig config) {
    int column = config.getColumn();
    int row = config.getRow();
    checkIndex(column, row);
    positions[column][row] = position;
  }

  /**
   * Initializes a new Position object on that position.
   *
   * @param column
   * @param row
   */
  @Override
  public void remove(int column, int row) {
    positions[column][row] = new Position(column, row);
  }

  /**
   * @param column The column in the 2D-array
   * @param row    The row in the 2D-array
   * @return Returns the Position object on that position
   */
  @Override
  public Position get(int column, int row) {
    return get(new Database2DConfig(column, row));
  }

  /**
   * @param config The position in the 2D-array
   * @return Returns the Position object on that position
   */
  @Override
  public Position get(Database2DConfig config) {
    int column = config.getColumn();
    int row = config.getRow();
    checkIndex(column, row);
    return positions[column][row];
  }

  /**
   * Returns true if the article in that positions == null
   *
   * @param column The column in the 2D-array
   * @param row    The row in the 2D-array
   * @return Returns if the article in that position is initialized
   */
  @Override
  public boolean isIndexEmpty(int column, int row) {
    return positions[column][row].isEmpty();
  }

  private boolean isIndexFull(int column, int row, String articleName) {
    if (positions[column][row].getArticle().getName().toLowerCase().equals(articleName.toLowerCase()) && !positions[column][row].isFull()) {
      return false;
    } else return !isIndexEmpty(column, row);
  }

  /**
   * Returns if the object is saved in the 2D-array. Uses the Position.equals() method
   *
   * @param o The object
   * @return Returns if the object is saved in the 2D-array.
   */
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

  /**
   * Returns an object representation of the next free position of the article in the 2D-array.
   * If there's already an article with that article name in the array but it's not full, this position gets returned.
   *
   * @param articleName The name of the article.
   * @return The position in the 2D-array
   */
  @Override
  public Database2DConfig getNextFreePosition(String articleName) {
    for (int i = 0; i < size.getColumn(); i++) {
      for (int j = 0; j < size.getRow(); j++) {
        if (isIndexEmpty(i, j) || !isIndexFull(i, j, articleName.toLowerCase())) {
          return new Database2DConfig(i, j);
        }
      }
    }
    return null;
  }

  /**
   * Returns a list of object representation of the positions in the 2D-array.
   * If a position's Article.name is the same as articleName, it gets added to the list.
   *
   * @param articleName The name of the article which gets searched
   * @return Returns a list of object representations of the positions in the 2D-array.
   */
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

  /**
   * Reinitialize all positions in the 2D-array.
   */
  @Override
  public void clear() {
    for (int i = 0; i < size.getColumn(); i++) {
      for (int j = 0; j < size.getRow(); j++) {
        positions[i][j] = new Position(i, j);
      }
    }
  }

  /**
   * @return Returns the 2D-Position array
   */
  @Override
  public Position[][] toArray() {
    return positions;
  }

  /**
   * @return Returns true, if all Position objects in the 2D-array are holding initialized Article objects.
   */
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

  /**
   * @return Returns an array of Position objects that hold initialized Article objects.
   */
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

  private int getNumberOfNonEmptyPositions() {
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

  /**
   * Sets the size of the 2D-array and initializes the new Position objects if it's bigger than before.
   *
   * @param columns The columns of the 2D-array
   * @param rows    The rows of the 2D-array
   */
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

  /**
   * Sets the size of the 2D-array and initializes the new Position objects if it's bigger than before.
   *
   * @param config
   */
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

  /**
   * Checks if the position is in range
   *
   * @param column The column of the 2D-array
   * @param row    The row of the 2D-array
   */
  private void checkIndex(int column, int row) {
    if (column < 0 || column > size.getColumn() || row < 0 || row > size.getRow()) {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  /**
   * Displays a '*' for Position full and a '_' for Position empty
   *
   * @return Returns a String representation of the 2D-array.
   */
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
