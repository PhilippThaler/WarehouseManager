package com.philippthaler.app.utils;

import com.philippthaler.app.App;
import com.philippthaler.app.commands.database.ControlCommandDatabase;
import com.philippthaler.app.database.ArticleDatabase;
import com.philippthaler.app.commands.database.ViewCommandDatabase;
import com.philippthaler.app.exceptions.PositionFullException;
import com.philippthaler.app.model.*;
import com.philippthaler.app.ui.UserInterface;
import com.philippthaler.app.ui.View;
import com.philippthaler.app.utils.helpers.Database2DConfig;
import com.philippthaler.app.utils.helpers.Price;
import com.philippthaler.app.utils.warehouse.GrowableArray2D;
import com.philippthaler.app.utils.warehouse.Position2DDatabase;

import java.util.List;
import java.util.Scanner;

/**
 * Controller class that controls the articleDatabase and die View
 */
public class Warehouse {

  private boolean running = true;
  private Scanner scanner;

  private ArticleDatabase articleDatabase;
  private ViewCommandDatabase viewCommands;
  private ControlCommandDatabase controlCommands;
  private GrowableArray2D<Position> warehousePositions;
  private View userInterface;

  private static final Warehouse instance = new Warehouse();

  private Warehouse() {
    scanner = new Scanner(System.in);
    viewCommands = new ViewCommandDatabase();
    articleDatabase = ArticleDatabase.getInstance();
    controlCommands = new ControlCommandDatabase();
    warehousePositions = new Position2DDatabase(App.COLUMN_INIT, App.ROW_INIT);
    userInterface = new UserInterface();
  }

  public static Warehouse getInstance() {
    return instance;
  }

  /**
   * Starts the warehouse management system
   */
  public void start() {
    System.out.println("------------------------");
    System.out.println("Warehouse Managing");
    System.out.println("------------------------");
    initWarehouse();
    while (running) {
      userInterface.start();
      listAllCommands();
      String command = scanner.next().toLowerCase();
      if (command.equals("q") || command.equals("exit")) {
        command = "quit";
      }
      scanner.nextLine();
      viewCommands.runCommand(command, userInterface);
      controlCommands.runCommand(command, this);
    }
  }

  /**
   * Initializes the warehouse with the values saved in the articleDatabase
   */
  private void initWarehouse() {
    for (String key : articleDatabase.getArticles().keySet()) {
      addArticle(articleDatabase.getArticle(key), 1);
    }
  }

  private void listAllCommands() {
    System.out.println();
    for (String command : viewCommands.getListOfCommands()) {
      System.out.print(" [" + command + "] ");
    }
    System.out.println();
  }

  /**
   * Displays all non empty warehouse positions
   */
  public void showAll() {
    Position[] nonEmpty = warehousePositions.getArrayOfNonEmptyPositions();
    for (Position p : nonEmpty) {
      System.out.println(p);
    }
  }

  /**
   * Method for resizing the warehouse
   */
  public void configWarehouse() {
    try {
      int columns = Integer.valueOf(scanner.next());
      int rows = Integer.valueOf(scanner.next());
      warehousePositions.setSize(columns, rows);
    } catch (NumberFormatException e) {
      System.out.println("Wrong kind of arguments. Integers expected");
    }
  }

  public void quit() {
    running = false;
  }

  /**
   * Method for the user input for adding an article.
   */
  public void addArticle() {
    String[] args = scanner.nextLine().split(" ");

    String name = "";
    int numOfArticles = 0;
    Article article = new ArticleNormal();

    try {
      if (args.length == 5) {
        name = args[0];
        Price price = new Price(args[1]);
        Supplier supplier = new Supplier(args[2]);
        PackagingUnit packagingUnit = new PackagingUnit(args[3]);
        numOfArticles = args.length < 5 ? 1 : Integer.valueOf(args[4]);
        article = new ArticleNormal(name, price, supplier, packagingUnit);
      } else if (args.length == 2 || args.length == 1) {
        name = args[0];
        article = new ArticleNormal(name);
        numOfArticles = args.length < 2 ? 1 : Integer.valueOf(args[1]);
      }

      if (isArticleNameInDb(name)) {
        addArticle(articleDatabase.getArticle(name), numOfArticles);
        return;
      }

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
      System.out.println(temp.getArticle().getName() + " added on position: " + temp.getArrayPosition());
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

  /**
   * @param article The article
   * @return Returns true if an Article with the same name is in the articleDatabase
   */
  private boolean isArticleInDb(Article article) {
    return articleDatabase.getArticle(article.getName().toLowerCase()) != null;
  }

  private boolean isArticleNameInDb(String articleName) {
    return isArticleInDb(new ArticleNormal(articleName, null, null, null));
  }

  public void removeArticle() {
    String name = scanner.next();

    List<Database2DConfig> positionList = warehousePositions.getPositions(name);
    if (positionList.isEmpty()) {
      System.out.println("There are no articles of " + name + " in the warehouse");
      return;
    }
    int sumOfArticles = 0;
    for (Database2DConfig config : positionList) {
      sumOfArticles += warehousePositions.get(config).getNumOfArticles();
    }
    System.out.println("There are " + sumOfArticles + " articles of " + name);
    System.out.println("How many would you like to remove? [q] to abort");
    String input = scanner.next();

    try {
      if (input.equals("q")) {
        return;
      }
      while (Integer.valueOf(input) <= 0 || Integer.valueOf(input) > sumOfArticles) {
        System.out.println("The warehouse has " + sumOfArticles + " of " + name + "!");
        input = scanner.next();
      }
      int amount = Integer.valueOf(input);
      for (Database2DConfig config : positionList) {
        Position temp = warehousePositions.get(config);
        if (temp.getNumOfArticles() >= amount) {
          // If there are more articles in the position, remove them all
          temp.subtractNumOfArticles(amount);
          if (temp.getNumOfArticles() == 0) {
            temp.setArticle(null);
          }
          return;
        } else {
          if (amount == 0) {
            return;
          }
          amount -= temp.getNumOfArticles();
          temp.subtractNumOfArticles(temp.getNumOfArticles());
          temp.setArticle(null);
        }
      }
    } catch (NumberFormatException e) {
      System.out.println("Wrong input. Aborting");
    }
  }


  public void deleteArticle() {
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

  public void getPositions() {
    String name = scanner.next();

    System.out.println("Positions for Article " + name + ":");
    for (Database2DConfig config : warehousePositions.getPositions(name)) {
      System.out.println(config);
    }
  }

  public void showPositionById() {
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
