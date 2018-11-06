package com.philippthaler.app.logic;

import com.philippthaler.app.database.ArticleDatabase;
import com.philippthaler.app.database.ViewCommandDatabase;
import com.philippthaler.app.exceptions.PositionFullException;
import com.philippthaler.app.ui.UserInterface;
import com.philippthaler.app.utils.Database2DConfig;
import com.philippthaler.app.utils.GrowableArray2D;
import com.philippthaler.app.utils.Position2DDatabase;
import com.philippthaler.app.utils.Price;

import java.util.List;
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
      if (command.equals("q") || command.equals("exit")) {
        command = "quit";
      }
      viewCommands.runCommand(command, userInterface);
      scanner.nextLine();
      switch (command) {
        case "add":
          addArticle();
          break;
        case "remove":
          removeArticle();
          break;
        case "inventory":
          showAll();
          break;
        case "config":
          configWarehouse();
          break;
        case "quit":
          running = false;
          break;
        case "position":
          getPositions();
          break;
        case "showposition":
          showPositionById();
          break;
        case "help":
        default:
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

  /**
   * Method for the user input for adding an article.
   */
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

  /**
   * Takes an article and the number of articles that should get saved.
   * If the number of articles is bigger than Position.getMaxAmountOfArticles(), the first Position gets filled
   * before it fills other Positions through recursion.
   *
   * @param article       The article that gets added
   * @param numOfArticles The number of articles, that gets saved in Position
   */
  private void addArticle(Article article, int numOfArticles) {
    try {
      Position temp = warehousePositions.get(warehousePositions.getNextFreePosition(article.getName()));
      temp.setArticle(article);
      temp.setNumOfArticles(numOfArticles);
    } catch (PositionFullException e) {
      Position temp = warehousePositions.get(warehousePositions.getNextFreePosition(article.getName()));
      // The amount of Articles that can still be saved into this position.
      int remainingSpace = Position.getMaxAmountOfArticles() - temp.getNumOfArticles();
      // The remainder that gets taken to the next iteration.
      int remainder = numOfArticles - remainingSpace;

      temp.setArticle(article);
      temp.addNumOfArticles(remainingSpace);
      addArticle(article, remainder);
      System.out.println(temp.getArticle().getName() + " added on position: " + temp.getArrayPosition());
    }

    addArticleToDb(article);
  }

  /**
   * Adds an article to the Article Database, if it's not in it yet.
   *
   * @param article The article
   */
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

    List<Database2DConfig> positionList = warehousePositions.getPositions(name);
    if (positionList.size() >= 1) {
      System.out.println("Warehouse Positions for " + name + ":");
      for (int i = 0; i < positionList.size(); i++) {
        System.out.println(i + ". " + positionList.get(i));
      }
      if (positionList.size() == 1) {
        System.out.println("Do you want to delete this item? [y/n]");
        if (scanner.next().toLowerCase().equals("y")) {
          Position temp = warehousePositions.get(positionList.get(0));
          temp.setArticle(null);
          temp.setNumOfArticles(0);
        }
      } else {
        System.out.println("Which position would you like to remove? [1, 2, 3, 1-3,...]");
        String input = scanner.next();
        if (input.length() == 1) {
          Position temp = warehousePositions.get(positionList.get(Integer.valueOf(input)));
          temp.setArticle(null);
          temp.setNumOfArticles(0);
        } else if (input.split("-").length == 2) {
          String[] range = input.split("-");

          for (int i = Integer.valueOf(range[0]); i < Integer.valueOf(range[1]); i++) {
            Position temp = warehousePositions.get(positionList.get(i));
            temp.setArticle(null);
            temp.setNumOfArticles(0);
          }
        }
      }
    }
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
