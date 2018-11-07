package com.philippthaler.app.ui;

/**
 * Implementation of the View interface.
 */
public class UserInterface implements View {

  @Override
  public void start() {
    System.out.println("\n\nWhat would you like to do?");
  }

  @Override
  public void quit() {
    System.out.println("Exiting the program...");
  }

  @Override
  public void remove() {
    System.out.println("Enter the article name that you'd like to remove: ");
  }

  @Override
  public void add() {
    System.out.println("Enter the Article Information, separated by whitespace");
    System.out.println("[Name] [Price] [Supplier] [Packaging Unit] [Number of Articles (default=1)]");
  }

  @Override
  public void delete() {
    System.out.println("Enter the article name: ");
  }

  @Override
  public void showHelp() {
    System.out.println("[add] - Add an article to the warehouse");
    System.out.println("[help] - Display this help menu");
    System.out.println("[showposition] - Shows what article is in a specific position (row/column)");
    System.out.println("[position] - Shows all warehouse positions of a specific article");
    System.out.println("[inventory] - Display all items in the warehouse");
    System.out.println("[config] - Warehouse configuration. Specify the size of the warehouse (rows/columns)");
    System.out.println("[remove] - Remove a number of articles from the warehouse");
    System.out.println("[delete] - Delete whole warehouse positions from the warehouse");
    System.out.println("[quit] - Exit the application");
  }

  @Override
  public void showAll() {
    System.out.println("Showing all entries...");
  }

  @Override
  public void config() {
    System.out.println("Enter the number of rows and columns you'd like, separated by whitespace");

  }

  @Override
  public void showPositions() {
    System.out.println("Enter the name of the article you'd like to search:");
  }

  @Override
  public void showPositionById() {
    System.out.println("Enter the column and the row, separated by whitespace");
  }


}
