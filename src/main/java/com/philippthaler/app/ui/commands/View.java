package com.philippthaler.app.ui.commands;

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
}
