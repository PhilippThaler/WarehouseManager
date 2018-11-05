package com.philippthaler.app.logic;

import com.philippthaler.app.database.ArticleDatabase;
import com.philippthaler.app.database.ViewCommandDatabase;
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

  public static boolean running = true;

  private ArticleDatabase articleDatabase;
  private ViewCommandDatabase viewCommands;
  private GrowableArray2D<Position> warehousePositions;
  private UserInterface userInterface;

  public Warehouse(int columns, int rows) {
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
    Scanner scanner = new Scanner(System.in);
    while (running) {
      userInterface.start();
      String command = scanner.next().toLowerCase();
      switch (command) {
        case "add":
          viewCommands.runCommand(command, userInterface);
          addArticle();
          break;
        case "inventory":
          viewCommands.runCommand(command, userInterface);
          showAll();
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

  private void addArticle() {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    String[] params = input.split(" ");

    try {
      String name = params[0];
      Price price = new Price(params[1]);
      Supplier supplier = new Supplier(params[2]);
      PackagingUnit packagingUnit = new PackagingUnit(params[3]);
      int numOfArticles = Integer.valueOf(params[4]);
      Article article = new ArticleNormal(name, price, supplier, packagingUnit);

      addArticle(article, numOfArticles);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Wrong number of arguments");
    } catch (NumberFormatException e) {
      System.out.println("Wrong arguments. Enter a number at the end");
    }
  }

  private void addArticle(Article article, int numOfArticles) {
    Position newPosition = new Position(article, warehousePositions.getNextFreePosition());
    newPosition.addNumOfArticles(numOfArticles);
    warehousePositions.add(newPosition);
    System.out.println(newPosition.getArticle().getName() + " added on position: " + newPosition.getArrayPosition());
  }

  private void addArticleToDb(Article article) {
    if (isArticleInDb(article)) {
      articleDatabase.putArticle(article.getName().toLowerCase(), (ArticleNormal) article);
    }
  }

  private boolean isArticleInDb(Article article) {
    return articleDatabase.getArticle(article.getName().toLowerCase()) != null;
  }

  private void removeArticle() {
    Scanner scanner = new Scanner(System.in);
    String name = scanner.next();

    // TODO
  }

  private void getPositions() {
    Scanner scanner = new Scanner(System.in);
    String name = scanner.next();

    System.out.println("Positions for Article " + name + ":");
    for (Database2DConfig config : warehousePositions.getPositions(name)) {
      System.out.println(config);
    }
  }

  private void showPositionById() {
    Scanner scanner = new Scanner(System.in);
    int column = Integer.valueOf(scanner.next());
    int row = Integer.valueOf(scanner.next());

    Position p = warehousePositions.get(column, row);
    System.out.println(!p.isEmpty() ? p : "There's nothing here");
  }
}
