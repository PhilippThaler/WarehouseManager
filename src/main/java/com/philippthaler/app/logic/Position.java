package com.philippthaler.app.logic;

public class Position<T> {

  private T obj;
  private int numOfObjects;
  private int column;
  private int row;

  public Position() {
    this(-1, -1);
  }

  public Position(int column, int row) {
    obj = null;
    numOfObjects = 0;
    this.column = column;
    this.row = row;
  }

  public T getObject() {
    return obj;
  }

  public int getNumOfObjects() {
    return numOfObjects;
  }

  public void setObject(T obj) {
    this.obj = obj;
  }

  public void setNumOfObjects(int numOfObjects) {
    this.numOfObjects = numOfObjects;
  }

  public void addNumOfObjects(int amount) {
    numOfObjects += amount;
  }

  public void subtractNumOfObjects(int amount) {
    if ((numOfObjects - amount) < 0) {
      numOfObjects = 0;
    }
    numOfObjects -= amount;
  }

  public boolean isEmpty() {
    return obj == null;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Position && ((Position) obj).obj.equals(this.obj) && ((Position) obj).numOfObjects == numOfObjects;

  }

  @Override
  public String toString() {
    return "Article: " + obj + ", Number: " + numOfObjects;
  }
}

