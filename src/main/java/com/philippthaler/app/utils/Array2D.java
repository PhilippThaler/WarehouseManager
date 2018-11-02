package com.philippthaler.app.utils;

/**
 * An interface for 2D Arrays
 * The user of this interface has control over where each element is inserted.
 * The user can access elements by their specific column and row indices and search for elements in the array.
 * @param <T>
 */
public interface Array2D<T> {
  void add(T object);
  void add(T object, int column, int row);
  void set(T object, int column, int row);
  void remove(int column, int row);
  T get(int column, int row);
  boolean isIndexEmpty(int column, int row);
  boolean contains(Object o);
  int getColumns();
  int getRows();
  void clear();
  boolean isFull();
  T[] getArrayOfNonEmptyPositions();
  T[][] toArray();

}
