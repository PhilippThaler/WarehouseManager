package com.philippthaler.app.database;

import java.util.Map;

public abstract class Database<T> {

  private Map<String, T> database = createDatabase();

  public Database() {

  }

  abstract Map<String, T> createDatabase();

  @Override
  public String toString() {
    return database.toString();
  }
}
