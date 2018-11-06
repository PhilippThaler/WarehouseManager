package com.philippthaler.app.utils.warehouse;

import com.philippthaler.app.utils.helpers.Database2DConfig;

import java.util.List;

public interface GrowableArray2D<T> extends Array2D<T> {
  void setColumns(int columns);
  void setRows(int rows);
  void setSize(int columns, int rows);
  void setSize(Database2DConfig config);
  Database2DConfig getNextFreePosition(String name);
  List<Database2DConfig> getPositions(String name);

}
