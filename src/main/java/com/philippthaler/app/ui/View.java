package com.philippthaler.app.ui;

/**
 * Interface that describes a user interface.
 */
public interface View {
  void start();
  void showHelp();
  void showAll();
  void config();
  void add();
  void showPositions();
  void showPositionById();
  void quit();
  void remove();
  void delete();
}
