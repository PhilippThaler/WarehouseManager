package com.philippthaler.app.database;

import com.philippthaler.app.model.ArticleInfo;
import com.philippthaler.app.model.ArticleNormal;
import com.philippthaler.app.model.PackagingUnit;
import com.philippthaler.app.utils.helpers.Price;
import com.philippthaler.app.model.Supplier;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple class for initializing a few values to the warehouse
 */
public class ArticleDatabase {

  private HashMap<String, PackagingUnit> packagingUnits;
  private HashMap<String, Supplier> suppliers;
  private HashMap<String, ArticleNormal> articles;

  private static final ArticleDatabase instance = new ArticleDatabase();

  private ArticleDatabase() {
    packagingUnits = createPackagingUnitMap();
    suppliers = createSupplierMap();
    articles = createArticleMap();
  }

  public static ArticleDatabase getInstance() {
    return instance;
  }

  private HashMap<String, ArticleNormal> createArticleMap() {
    Map<String, ArticleNormal> temp = Map.of(
        "milch", new ArticleNormal("Milch", new Price(1), suppliers.get("grissemann"), packagingUnits.get("tetrapak")),
        "mehl", new ArticleNormal("Mehl", new Price(2), suppliers.get("grissemann"), packagingUnits.get("sack")),
        "cola", new ArticleNormal("Cola", new Price(3), suppliers.get("wedl"), packagingUnits.get("palette")),
        "rindfleisch", new ArticleNormal("Rindfleisch", new Price(20), suppliers.get("fleischhof"), packagingUnits.get("kg"))
    );
    return new HashMap<>(temp);
  }

  private HashMap<String, PackagingUnit> createPackagingUnitMap() {
    Map<String, PackagingUnit> temp = Map.of(
        "tetrapak", new PackagingUnit("Tetrapak"),
        "sack", new PackagingUnit("Sack"),
        "palette", new PackagingUnit("Palette"),
        "fass", new PackagingUnit("Fass"),
        "kg", new PackagingUnit("kg")
    );
    return new HashMap<>(temp);
  }

  private HashMap<String, Supplier> createSupplierMap() {
    Map<String, Supplier> temp = Map.of(
        "grissemann", new Supplier("Grissemann"),
        "wedl", new Supplier("C&C Wedl"),
        "fleischhof", new Supplier("Fleischhof Oberland")
    );
    return new HashMap<>(temp);
  }

  public ArticleNormal getArticle(String key) {
    return articles.get(key.toLowerCase());
  }

  public PackagingUnit getPackagingUnit(String key) {
    return packagingUnits.get(key.toLowerCase());
  }

  public Supplier getSupplier(String key) {
    return suppliers.get(key.toLowerCase());
  }

  public HashMap<String, ArticleNormal> getArticles() {
    return articles;
  }

  public ArticleInfo getArticleInfo(String key) {
    if (suppliers.get(key.toLowerCase()) != null) {
      return suppliers.get(key.toLowerCase());
    } else if (packagingUnits.get(key.toLowerCase()) != null) {
      return packagingUnits.get(key.toLowerCase());
    }
    return null;
  }

  public void putArticleInfo(String key, ArticleInfo info) {
    if (info instanceof PackagingUnit) {
      packagingUnits.put(key.toLowerCase(), (PackagingUnit) info);
    } else if (info instanceof Supplier) {
      suppliers.put(key.toLowerCase(), (Supplier) info);
    }
  }

  public void putArticle(String key, ArticleNormal article) {
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
