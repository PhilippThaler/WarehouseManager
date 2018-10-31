package com.philippthaler.app.logic;

import com.philippthaler.app.database.Database;
import com.philippthaler.app.utils.Array2D;
import com.philippthaler.app.utils.PositionArray2D;

/**
 * Controller class, that controls the database and die View
 */
public class Warehouse {

  private Database database;
  private Array2D<Position> warehousePositions;

  public Warehouse(int columns, int rows) {
    database = Database.getInstance();
    warehousePositions=new PositionArray2D(columns, rows);
  }



}
