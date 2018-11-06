package com.philippthaler.app.database;

import com.philippthaler.app.ui.UserInterface;
import com.philippthaler.app.ui.commands.Command;
import com.philippthaler.app.ui.commands.View;

import java.util.*;

public class ViewCommandDatabase {
  private static HashMap<String, Command> commands;

  public ViewCommandDatabase() {
    commands = initDatabase();

  }

  public HashMap<String, Command> initDatabase() {
    HashMap<String, Command> temp = new HashMap<>();
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
      commands.get("help").run(ui);
      return;
    }
    commands.get(command).run(ui);
  }

  public static String[] getListOfCommands() {
    String[] commandStrings = commands.keySet().toArray(new String[0]);

    return commandStrings;
  }
}
