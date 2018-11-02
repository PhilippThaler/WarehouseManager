package com.philippthaler.app.database;

import com.philippthaler.app.ui.UserInterface;
import com.philippthaler.app.ui.commands.Command;
import com.philippthaler.app.ui.commands.View;

import java.util.HashMap;
import java.util.Set;

public class ViewCommandDatabase {
  HashMap<String, Command> commands;

  public ViewCommandDatabase() {
    commands = initDatabase();

  }

  public HashMap<String, Command> initDatabase() {
    HashMap<String, Command> temp = new HashMap<>();
    temp.put("help", View::showHelp);
    temp.put("start", View::start);
    temp.put("showall", View::showAll);
    temp.put("config", View::config);
    temp.put("add", View::add);

    return temp;
  }

  public void runCommand(String command, UserInterface ui) {
    if (commands.get(command) == null) {
      commands.get("help").run(ui);
      return;
    }
    commands.get(command).run(ui);
  }

  public Object[] getListOfCommands() {
    Set commandStrings = commands.keySet();
    return commandStrings.toArray();
  }
}
