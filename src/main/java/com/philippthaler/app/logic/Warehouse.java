package com.philippthaler.app.logic;

import com.philippthaler.app.utils.Array2D;
import com.philippthaler.app.utils.PositionArray2D;

public class Warehouse {

  private Array2D<Position> warehousePositions;


  public Warehouse(int columns, int rows) {
    warehousePositions=new PositionArray2D(columns, rows);
  }
}
