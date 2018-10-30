package com.philippthaler.app;

import com.philippthaler.app.utils.Position2DArray;

import java.math.BigDecimal;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) {
    Position2DArray array = new Position2DArray(10, 10);
    Article milch = new Article("Milch", "1", new Price(new BigDecimal(100), "€"), new Supplier("Grissemann"), new PackagingUnit("Tetrapak"));
    Article penis = new Article("penis", "1", new Price(new BigDecimal(100), "€"), new Supplier("Grissemann"), new PackagingUnit("Tetrapak"));

    array.add(milch, 1, 1);
    array.setNumberOfArticles(10,1,1);

    System.out.println(array.get(1,1));

    array.setPosition(penis, 1,1);

    System.out.println(array.get(1,1));

  }
}
