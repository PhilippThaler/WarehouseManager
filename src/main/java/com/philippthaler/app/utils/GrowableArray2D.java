package com.philippthaler.app.utils;

public interface GrowableArray2D<T> extends Array2D<T> {
  void setColumns(int columns);
  void setRows(int rows);
  void setSize(int columns, int rows);
  void setSize(Database2DConfig config);

}
