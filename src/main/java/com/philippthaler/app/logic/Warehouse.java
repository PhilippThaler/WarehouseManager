package com.philippthaler.app.logic;

import com.philippthaler.app.database.ArticleDatabase;
import com.philippthaler.app.database.ViewCommandDatabase;
import com.philippthaler.app.ui.UserInterface;
import com.philippthaler.app.utils.Database2DConfig;
import com.philippthaler.app.utils.GrowableArray2D;
import com.philippthaler.app.utils.Position2DDatabase;
import com.philippthaler.app.utils.Price;

import java.util.Scanner;
import java.util.Set;

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
          running = false;
          break;
        case "position":
          viewCommands.runCommand(command, userInterface);
          getPosition();
          break;
        case "help":
        default:
          viewCommands.runCommand(command, userInterface);
          break;
      }
    }
  }

  private void initWarehouse() {
    for(String key : articleDatabase.createArticleMap().keySet()) {
      addArticle(articleDatabase.getArticle(key));
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
    String name = scanner.next();
    Price price = new Price(scanner.next());
    Supplier supplier = new Supplier(scanner.next());
    PackagingUnit packagingUnit = new PackagingUnit(scanner.next());
    Article article = new ArticleNormal(name, "2", price, supplier, packagingUnit);

    addArticle(article);
  }

  private void addArticle(Article article) {
    Position newPosition = new Position(article, warehousePositions.getNextFreePosition());
    warehousePositions.add(newPosition);
    System.out.println(newPosition.getArticle().getName() + " added on position: " + newPosition.getArrayPosition());
  }

  private void removeArticle() {
    Scanner scanner = new Scanner(System.in);
    String name = scanner.next();

    // TODO
  }

  private void getPosition() {
    Scanner scanner = new Scanner(System.in);
    String name = scanner.next();

    System.out.println("Positions for Article " + name + ":");
    for(Database2DConfig config : warehousePositions.getPositions(name)) {
      System.out.println(config);
    }
  }
}
