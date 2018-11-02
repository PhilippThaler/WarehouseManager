package com.philippthaler.app;

import com.philippthaler.app.database.Database;
import com.philippthaler.app.logic.*;
import com.philippthaler.app.utils.GrowableArray2D;
import com.philippthaler.app.utils.PositionArray2D;
import com.philippthaler.app.utils.Price;

import java.math.BigDecimal;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) {
    GrowableArray2D array = new PositionArray2D(1, 1);
    Article milch = new Article("Milch", "1", new Price(new BigDecimal(125), "€"), new Supplier("Grissemann"), new PackagingUnit("Tetrapak"));
    Article penis = new Article("penis", "1", new Price(new BigDecimal(100), "€"), new Supplier("Grissemann"), new PackagingUnit("Tetrapak"));
    Article tempo = new Article("Tempo", "1", new Price(new BigDecimal(321), "€"), new Supplier("Grissemann"), new PackagingUnit("Tetrapak"));

    Position<Article> milchP = new Position<>(milch);
    Position penisP = new Position(penis);
    array.add(milchP);

    array.add(new Position<Article>(penis));
    array.add(new Position<Article>(tempo));

    System.out.println(array);
    array.setRows(array.getRows() + 1);
    System.out.println(array);
    System.out.println(array.contains(milchP));
    System.out.println(array.contains(penisP));
    array.remove(0, 0);

    System.out.println(array);

    Database x = Database.getInstance();
    x.removeArticle("milch");

  }
}
