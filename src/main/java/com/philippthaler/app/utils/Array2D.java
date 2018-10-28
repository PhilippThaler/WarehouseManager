package com.philippthaler.app.utils;

import com.philippthaler.app.Position;

public class Array2D {

  private Position[][] positions;

  public Array2D() {
    positions = new Position[0][0];
  }

  public Array2D(int columns, int rows) {
    positions = new Position[columns][rows];
  }

  public void setNumOfRows(int rows) {

  }

  public void setNumOfColumns(int columns) {

  }

}
