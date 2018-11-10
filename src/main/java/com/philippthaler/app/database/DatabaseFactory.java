package com.philippthaler.app.database;

public class DatabaseFactory {
  public Database create(String type) {
    switch (type) {
      case "mariadb":
        return new MariaDatabase();
      default:
        throw new IllegalArgumentException("Can't create database of type: " + type);
    }
  }
}
