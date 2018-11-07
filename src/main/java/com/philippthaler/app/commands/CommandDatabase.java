package com.philippthaler.app.commands;

import java.util.HashMap;

public interface CommandDatabase<T> {
  HashMap<String, Command<T>> getDatabase();
  void runCommand(String command, T user);
  String[] getListOfCommands();
}
