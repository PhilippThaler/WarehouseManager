package com.philippthaler.app.utils;

import com.philippthaler.app.Article;
import com.philippthaler.app.Position;
import com.philippthaler.app.exceptions.PositionTakenException;

public class Position2DArray {

  private Position[][] positions;
  private int columns;
  private int rows;

  public Position2DArray(int columns, int rows) {
    this.columns = columns;
    this.rows = rows;
    positions = new Position[columns][rows];
    for (int i = 0; i < this.columns; i++) {
      for (int j = 0; j < this.rows; j++) {
        positions[i][j] = new Position();
      }
    }
  }

  public void add(Article article, int column, int row) {
    checkIndex(column, row);
    if (!isEmpty(column,row)) {
      throw new PositionTakenException("Position already taken: " + column + "," + row);
    }
    positions[column][row].setArticle(article);
  }

  public void setPosition(Article article, int column, int row) {
    checkIndex(column, row);
    positions[column][row].setArticle(article);
  }

  public void setNumberOfArticles(int numOfArticles, int column, int row) {
    checkIndex(column, row);
    positions[column][row].setNumOfArticles(numOfArticles);
  }

  public void remove(int column, int row) {
    positions[column][row] = null;
  }

  public Position get(int column, int row) {
    checkIndex(column, row);
    return positions[column][row];
  }

  public boolean isEmpty(int column, int row) {
    return positions[column][row] == null;
  }

  private void checkIndex(int column, int row) {
    if (column < 0 || column > columns - 1 || row < 0 || row > rows - 1) {
      throw new ArrayIndexOutOfBoundsException();
    }
  }
}
