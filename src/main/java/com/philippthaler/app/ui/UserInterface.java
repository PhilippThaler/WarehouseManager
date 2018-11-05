package com.philippthaler.app.ui;

import com.philippthaler.app.database.ViewCommandDatabase;
import com.philippthaler.app.ui.commands.View;

public class UserInterface implements View {

  public UserInterface() {

  }

  @Override
  public void start() {
    System.out.println("\n\nWhat would you like to do?");
    showHelp();
  }

  @Override
  public void quit() {
    System.out.println("Exiting the program...");
  }

  public void add() {
    System.out.println("Enter the Article Information, separated by whitespace");
    System.out.println("[Name] [Price] [Supplier] [Packaging Unit] [Number of Articles (default=1)]");
  }

  public void remove() {
    System.out.println("Enter the article name: ");
  }

  @Override
  public void showHelp() {
    System.out.println();
    for(String command: ViewCommandDatabase.getListOfCommands()) {
      System.out.print(" ["+command+"] ");
    }
    System.out.println();
  }

  public void showAll() {
    System.out.println("Show All");
  }

  public void config() {
    System.out.println("Enter the number of rows and columns you'd like, separated by whitespace");

  }

  public void showPositions() {
    System.out.println("Enter the name of the article you'd like to search:");
  }

  public void showPositionById() {
    System.out.println("Enter the column and the row, separated by whitespace");
  }


}
