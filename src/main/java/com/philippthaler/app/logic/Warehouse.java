package com.philippthaler.app.logic;

import com.philippthaler.app.database.ArticleDatabase;
import com.philippthaler.app.database.ViewCommandDatabase;
import com.philippthaler.app.ui.UserInterface;
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
    Scanner scanner = new Scanner(System.in);
    while (running) {
      userInterface.start();
      switch (scanner.next().toLowerCase()) {
        case "add":
          viewCommands.runCommand("add", userInterface);
          addArticle();
          break;
        case "showall":
          viewCommands.runCommand("showall", userInterface);
          showAll();
          break;
        case "q":
        case "quit":
          running = false;
          break;
        case "position":
          getPosition();
          break;
        case "help":
        default:
          viewCommands.runCommand("help", userInterface);
          break;
      }
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
    Article article = new Article(name, "2", price, supplier, packagingUnit);

    warehousePositions.add(new Position(article));
  }

  private void getPosition() {

  }
}
