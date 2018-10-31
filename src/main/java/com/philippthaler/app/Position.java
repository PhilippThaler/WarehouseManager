package com.philippthaler.app;

import java.util.InputMismatchException;

public class Position<T> {

  private T obj;
  private int numOfObjects;

  public Position() {
    obj = null;
    numOfObjects = 0;
  }

  public Position(T obj) {
    this.obj = obj;
    numOfObjects = 0;
  }

  public Position(T obj, int numOfObjects) {
    if (numOfObjects < 0) {
      throw new InputMismatchException("The number can't be negative!");
    }
    this.obj = obj;
    this.numOfObjects = numOfObjects;
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

