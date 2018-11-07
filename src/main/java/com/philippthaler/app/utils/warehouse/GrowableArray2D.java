package com.philippthaler.app.utils.warehouse;

import com.philippthaler.app.utils.helpers.Database2DConfig;

import java.util.List;

/**
 * Interface that extends the Array2D interface to be configurable.
 * Describes methods for making it bigger and smaller.
 *
 * @param <T> Instances of this type can be saved here.
 */
public interface GrowableArray2D<T> extends Array2D<T> {
  void setColumns(int columns);

  void setRows(int rows);

  void setSize(int columns, int rows);

  void setSize(Database2DConfig config);

  Database2DConfig getNextFreePosition(String name);

  List<Database2DConfig> getPositions(String name);

}
