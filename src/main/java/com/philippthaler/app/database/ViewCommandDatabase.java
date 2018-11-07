package com.philippthaler.app.database;

import com.philippthaler.app.ui.UserInterface;
import com.philippthaler.app.commands.ViewCommand;
import com.philippthaler.app.ui.View;

import java.util.*;

/**
 * Simple command database, that holds many commands for the user interface
 */
public class ViewCommandDatabase {
  private static HashMap<String, ViewCommand> commands;

  public ViewCommandDatabase() {
    commands = initDatabase();

  }

  private HashMap<String, ViewCommand> initDatabase() {
    HashMap<String, ViewCommand> temp = new HashMap<>();
    temp.put("help", View::showHelp);
    temp.put("inventory", View::showAll);
    temp.put("config", View::config);
    temp.put("add", View::add);
    temp.put("position", View::showPositions);
    temp.put("showposition", View::showPositionById);
    temp.put("delete", View::delete);
    temp.put("remove", View::remove);
    temp.put("quit", View::quit);

    return temp;
  }

  /**
   * Method for running commands. Runs the help method, when the command can't be found.
   * Takes an instance of the ui class, on which it should be run.
   * @param command The command that should be run
   * @param ui The ui instance.
   */
  public void runCommand(String command, UserInterface ui) {
    if (commands.get(command) == null) {
      commands.get("help").execute(ui);
      return;
    }
    commands.get(command).execute(ui);
  }

  /**
   * @return An array of Strings for all the commands
   */
  public static String[] getListOfCommands() {
    return commands.keySet().toArray(new String[0]);
  }
}
