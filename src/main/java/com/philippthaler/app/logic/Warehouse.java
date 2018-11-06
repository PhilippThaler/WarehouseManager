package com.philippthaler.app.logic;

import com.philippthaler.app.database.ArticleDatabase;
import com.philippthaler.app.database.ViewCommandDatabase;
import com.philippthaler.app.exceptions.PositionFullException;
import com.philippthaler.app.ui.UserInterface;
import com.philippthaler.app.utils.Database2DConfig;
import com.philippthaler.app.utils.GrowableArray2D;
import com.philippthaler.app.utils.Position2DDatabase;
import com.philippthaler.app.utils.Price;

import java.util.Scanner;

/**
 * Controller class that controls the articleDatabase and die View
 */
public class Warehouse {

  private static boolean running = true;
  private Scanner scanner;

  private ArticleDatabase articleDatabase;
  private ViewCommandDatabase viewCommands;
  private GrowableArray2D<Position> warehousePositions;
  private UserInterface userInterface;

  public Warehouse(int columns, int rows) {
    scanner = new Scanner(System.in);
    viewCommands = new ViewCommandDatabase();
    articleDatabase = ArticleDatabase.getInstance();
    warehousePositions = new Position2DDatabase(columns, rows);
    userInterface = new UserInterface();
  }

  public void startUI() {
    System.out.println("------------------------");
    System.out.println("Warehouse Managing");
    System.out.println("------------------------");
    initWarehouse();
    while (running) {
      userInterface.start();
      String command = scanner.next().toLowerCase();
      scanner.nextLine();
      switch (command) {
        case "add":
          viewCommands.runCommand(command, userInterface);
          addArticle();
          break;
        case "inventory":
          viewCommands.runCommand(command, userInterface);
          showAll();
          break;
        case "config":
          viewCommands.runCommand(command, userInterface);
          configWarehouse();
          break;
        case "q":
        case "quit":
        case "exit":
          viewCommands.runCommand("quit", userInterface);
          running = false;
          break;
        case "position":
          viewCommands.runCommand(command, userInterface);
          getPositions();
          break;
        case "showposition":
          viewCommands.runCommand(command, userInterface);
          showPositionById();
          break;
        case "help":
        default:
          viewCommands.runCommand(command, userInterface);
          break;
      }
    }
  }

  private void initWarehouse() {
    for (String key : articleDatabase.createArticleMap().keySet()) {
      addArticle(articleDatabase.getArticle(key), 1);
    }
  }

  private void showAll() {
    Position[] nonEmpty = warehousePositions.getArrayOfNonEmptyPositions();

    for (Position p : nonEmpty) {
      System.out.println(p);
    }
  }

  private void configWarehouse() {
    try {
      int columns = Integer.valueOf(scanner.next());
      int rows = Integer.valueOf(scanner.next());
      warehousePositions.setSize(columns, rows);
    } catch (NumberFormatException e) {
      System.out.println("Wrong kind of arguments. Integers expected");
    }
  }

  private void addArticle() {
    String[] args = scanner.nextLine().split(" ");

    try {
      String name = args[0];
      Price price = new Price(args[1]);
      Supplier supplier = new Supplier(args[2]);
      PackagingUnit packagingUnit = new PackagingUnit(args[3]);
      int numOfArticles = args.length < 5 ? 1 : Integer.valueOf(args[4]);
      Article article = new ArticleNormal(name, price, supplier, packagingUnit);

      addArticle(article, numOfArticles);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Wrong number of arguments");
    } catch (NumberFormatException e) {
      System.out.println("Wrong arguments. Enter a number at the end");
    }
  }

  private void addArticle(Article article, int numOfArticles) {
    try {
      Position temp = warehousePositions.get(warehousePositions.getNextFreePosition(article.getName()));
      temp.setArticle(article);
      temp.setNumOfArticles(numOfArticles);
    } catch (PositionFullException e) {
      Position temp = warehousePositions.get(warehousePositions.getNextFreePosition(article.getName()));
      // The amount of Articles that can still be saved into this position.
      int remainingSpace = Position.getMaxAmountOfArticles() - temp.getNumOfArticles();
      int remainder = numOfArticles - remainingSpace;
      temp.setArticle(article);
      temp.addNumOfArticles(remainingSpace);
      addArticle(article, remainder);
      System.out.println(temp.getArticle().getName() + " added on position: " + temp.getArrayPosition());
    }

    addArticleToDb(article);
  }

  private void addArticleToDb(Article article) {
    if (!isArticleInDb(article)) {
      articleDatabase.putArticle(article.getName().toLowerCase(), (ArticleNormal) article);
      System.out.println(article.getName() + " added to the database.");
    }
  }

  private boolean isArticleInDb(Article article) {
    return articleDatabase.getArticle(article.getName().toLowerCase()) != null;
  }

  private void removeArticle() {
    String name = scanner.next();

    // TODO
  }

  private void getPositions() {
    String name = scanner.next();

    System.out.println("Positions for Article " + name + ":");
    for (Database2DConfig config : warehousePositions.getPositions(name)) {
      System.out.println(config);
    }
  }

  private void showPositionById() {
    int column, row;

    try {
      column = Integer.valueOf(scanner.next());
      row = Integer.valueOf(scanner.next());

      Position p = warehousePositions.get(column, row);
      System.out.println(!p.isEmpty() ? p : "There's nothing here");
    } catch (NumberFormatException e) {
      System.out.println("Wrong kind of arguments. Integers expected");
    }
  }
}
