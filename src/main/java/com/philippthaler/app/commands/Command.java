package com.philippthaler.app.commands;

public interface Command<T> {
  void execute(T user);
}
