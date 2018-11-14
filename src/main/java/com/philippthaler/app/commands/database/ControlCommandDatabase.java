package com.philippthaler.app.commands.database;

import com.philippthaler.app.commands.Command;
import com.philippthaler.app.commands.CommandDatabase;
import com.philippthaler.app.utils.Warehouse;

import java.util.HashMap;

public class ControlCommandDatabase implements CommandDatabase<Warehouse> {
  private HashMap<String, Command<Warehouse>> commands;

  public ControlCommandDatabase() {
    commands = getDatabase();

  }

  @Override
  public HashMap<String, Command<Warehouse>> getDatabase() {
    HashMap<String, Command<Warehouse>> temp = new HashMap<>();
    temp.put("inventory", Warehouse::showAll);
    temp.put("config", Warehouse::configWarehouse);
    temp.put("add", Warehouse::addArticle);
    temp.put("position", Warehouse::getPositions);
    temp.put("showposition", Warehouse::showPositionById);
    temp.put("delete", Warehouse::deleteArticle);
    temp.put("quit", Warehouse::quit);
    temp.put("remove", Warehouse::removeArticle);

    return temp;
  }

  @Override
  public void runCommand(String command, Warehouse warehouse) {
    if (commands.get(command) == null) {
      return;
    }
    commands.get(command).execute(warehouse);
  }

  @Override
  public String[] getListOfCommands() {
    return commands.keySet().toArray(new String[0]);
  }
}