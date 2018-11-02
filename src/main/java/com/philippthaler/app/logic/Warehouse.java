package com.philippthaler.app.logic;

import com.philippthaler.app.database.ArticleDatabase;
import com.philippthaler.app.database.CommandDatabase;
import com.philippthaler.app.ui.UserInterface;
import com.philippthaler.app.utils.GrowableArray2D;
import com.philippthaler.app.utils.PositionArray2D;

import java.util.Scanner;

/**
 * Controller class that controls the articleDatabase and die View
 */
public class Warehouse {

  public static boolean running = true;

  private ArticleDatabase articleDatabase;
  private CommandDatabase commandDatabase;
  private GrowableArray2D<Position> warehousePositions;
  private UserInterface userInterface;

  public Warehouse(int columns, int rows) {
    commandDatabase = new CommandDatabase();
    articleDatabase = ArticleDatabase.getInstance();
    warehousePositions = new PositionArray2D(columns, rows);
    userInterface = new UserInterface();
  }

  public void startUI() {
    userInterface.start();
    Scanner scanner = new Scanner(System.in);
    while(running) {
      commandDatabase.runCommand(scanner.next(), userInterface);
    }
  }

}
