package com.philippthaler.app.ui;

import com.philippthaler.app.database.ViewCommandDatabase;
import com.philippthaler.app.ui.commands.Command;
import com.philippthaler.app.ui.commands.View;

public class UserInterface implements View {

  public UserInterface() {

  }

  @Override
  public void start() {
    System.out.println("\n\nWhat would you like to do?");
    showHelp();
  }

  public void add() {
    System.out.println("Enter the Article Information, seperated by whitespace");
    System.out.println("[Name] [Price] [Supplier] [Packaging Unit]");
  }

  @Override
  public void input() {
    System.out.println("input");
  }

  @Override
  public void showHelp() {
    for(String command: ViewCommandDatabase.getListOfCommands()) {
      System.out.print(" ["+command+"] ");
    }
  }

  public void showAll() {
    System.out.println("Show All");
  }

  public void config() {
    System.out.println("Enter the number of rows and columns you'd like, seperated by whitespace");

  }


}
