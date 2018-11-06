package com.philippthaler.app.ui.commands;

public interface Command<T> {
  void run(T command);
}
