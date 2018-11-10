package com.philippthaler.app.database;

public interface Database {
  void drop();
  void create();
  void runScript();
  void compare();
  void disconnect();
}
