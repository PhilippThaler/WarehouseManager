package com.philippthaler.app.database;

import com.philippthaler.app.logic.Article;
import com.philippthaler.app.logic.ArticleInfo;
import com.philippthaler.app.logic.PackagingUnit;
import com.philippthaler.app.utils.Price;
import com.philippthaler.app.logic.Supplier;

import java.util.Map;

public class Database {

  private Map<String, Article> articles = createArticleMap();
  private Map<String, PackagingUnit> packagingUnits = createPackagingUnitMap();
  private Map<String, Supplier> suppliers = createSupplierMap();

  private static final Database instance = new Database();

  private Database() {

  }

  public static Database getInstance() {
    return instance;
  }

  public Map<String, Article> createArticleMap() {
    Map<String, Article> articles = Map.of(
        "milch", new Article("Milch", "1", new Price(1), suppliers.get("grissemann"), packagingUnits.get("tetrapak")),
        "mehl", new Article("Mehl", "2", new Price(2), suppliers.get("grissemann"), packagingUnits.get("sack")),
        "cola", new Article("Cola", "3", new Price(3), suppliers.get("wedl"), packagingUnits.get("palette")),
        "rindfleisch", new Article("Rindfleisch", "5", new Price(20), suppliers.get("fleischhof"), packagingUnits.get("kg"))
    );

    return articles;
  }

  public Map<String, PackagingUnit> createPackagingUnitMap() {
    Map<String, PackagingUnit> packagingUnits = Map.of(
        "tetrapak", new PackagingUnit("Tetrapak"),
        "sack", new PackagingUnit("Sack"),
        "palette", new PackagingUnit("Palette"),
        "fass", new PackagingUnit("Fass"),
        "kg", new PackagingUnit("kg")
    );

    return packagingUnits;
  }

  public Map<String, Supplier> createSupplierMap() {
    Map<String, Supplier> suppliers = Map.of(
        "grissemann", new Supplier("Grissemann"),
        "wedl", new Supplier("C&C Wedl"),
        "fleischhof", new Supplier("Fleischhof Oberland")
    );

    return suppliers;
  }

  public Article getArticle(String key) {
    return articles.get(key.toLowerCase());
  }

  public PackagingUnit getPackagingUnit(String key) {
    return packagingUnits.get(key.toLowerCase());
  }

  public Supplier getSupplier(String key) {
    return suppliers.get(key.toLowerCase());
  }

  public void putArticleInfo(String key, ArticleInfo info) {
    if (info instanceof PackagingUnit) {
      packagingUnits.put(key.toLowerCase(), (PackagingUnit) info);
    } else if (info instanceof Supplier) {
      suppliers.put(key.toLowerCase(), (Supplier) info);
    }
  }

  public void putArticle(String key, Article article) {
    articles.put(key.toLowerCase(), article);
  }

  public void removeArticle(String key) {
    articles.remove(key.toLowerCase());
  }

  public void removePackagingUnit(String key) {
    packagingUnits.remove(key.toLowerCase());
  }

  public void removeSupplier(String key) {
    suppliers.remove(key.toLowerCase());
  }

}
