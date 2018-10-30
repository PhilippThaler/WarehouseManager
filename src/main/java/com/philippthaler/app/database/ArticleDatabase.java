package com.philippthaler.app.database;

import com.philippthaler.app.Article;
import com.philippthaler.app.PackagingUnit;
import com.philippthaler.app.Price;
import com.philippthaler.app.Supplier;

import java.util.Map;

public class ArticleDatabase extends Database {

  private Map<String, Article> articles = createDatabase();

  public ArticleDatabase() {

  }

  Map<String, Article> createDatabase() {
    Map<String, Article> articles = Map.of(
        "Milch", new Article("Milch", "1", new Price(1, "€"), new Supplier("Grissemann"), new PackagingUnit("Tetrapak"))
    );

    return articles;
  }


}
