package com.philippthaler.app.database;

import com.philippthaler.app.ui.UserInterface;
import com.philippthaler.app.commands.ViewCommand;
import com.philippthaler.app.ui.View;

import java.util.*;

public class ViewCommandDatabase {
  private static HashMap<String, ViewCommand> commands;

  public ViewCommandDatabase() {
    commands = initDatabase();

  }

  public HashMap<String, ViewCommand> initDatabase() {
    HashMap<String, ViewCommand> temp = new HashMap<>();
    temp.put("help", View::showHelp);
    temp.put("inventory", View::showAll);
    temp.put("config", View::config);
    temp.put("add", View::add);
    temp.put("position", View::showPositions);
    temp.put("showposition", View::showPositionById);
    temp.put("quit", View::quit);
    temp.put("remove", View::remove);

    return temp;
  }

  public void runCommand(String command, UserInterface ui) {
    if (commands.get(command) == null) {
      commands.get("help").execute(ui);
      return;
    }
    commands.get(command).execute(ui);
  }

  public static String[] getListOfCommands() {
    String[] commandStrings = commands.keySet().toArray(new String[0]);

    return commandStrings;
  }
}
