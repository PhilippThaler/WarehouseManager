package com.philippthaler.app.commands.database;

import com.philippthaler.app.commands.Command;
import com.philippthaler.app.commands.CommandDatabase;
import com.philippthaler.app.ui.View;

import java.util.*;

/**
 * Simple command database, that holds many commands for the user interface
 */
public class ViewCommandDatabase implements CommandDatabase<View> {
  private static HashMap<String, Command<View>> commands;

  public ViewCommandDatabase() {
    commands = getDatabase();

  }

  @Override
  public HashMap<String, Command<View>> getDatabase() {
    HashMap<String, Command<View>> temp = new HashMap<>();
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
   *
   * @param command The command that should be run
   * @param ui      The ui instance.
   */
  @Override
  public void runCommand(String command, View ui) {
    if (commands.get(command) == null) {
      commands.get("help").execute(ui);
      return;
    }
    commands.get(command).execute(ui);
  }

  /**
   * @return An array of Strings for all the commands
   */
  @Override
  public String[] getListOfCommands() {
    return commands.keySet().toArray(new String[0]);
  }
}
