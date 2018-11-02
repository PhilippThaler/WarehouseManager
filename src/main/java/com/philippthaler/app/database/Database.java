package com.philippthaler.app.database;

import com.philippthaler.app.logic.Article;
import com.philippthaler.app.logic.ArticleInfo;
import com.philippthaler.app.logic.PackagingUnit;
import com.philippthaler.app.utils.Price;
import com.philippthaler.app.logic.Supplier;

import java.util.HashMap;
import java.util.Map;

public class Database {

  private HashMap<String, PackagingUnit> packagingUnits;
  private HashMap<String, Supplier> suppliers;
  private HashMap<String, Article> articles;

  private static final Database instance = new Database();

  private Database() {
    packagingUnits = createPackagingUnitMap();
    suppliers = createSupplierMap();
    articles = createArticleMap();
  }

  public static Database getInstance() {
    return instance;
  }

  public HashMap<String, Article> createArticleMap() {
    Map<String, Article> temp = Map.of(
        "milch", new Article("Milch", "1", new Price(1), suppliers.get("grissemann"), packagingUnits.get("tetrapak")),
        "mehl", new Article("Mehl", "2", new Price(2), suppliers.get("grissemann"), packagingUnits.get("sack")),
        "cola", new Article("Cola", "3", new Price(3), suppliers.get("wedl"), packagingUnits.get("palette")),
        "rindfleisch", new Article("Rindfleisch", "5", new Price(20), suppliers.get("fleischhof"), packagingUnits.get("kg"))
    );
    HashMap<String, Article> map = new HashMap<>();
    map.putAll(temp);
    return map;
  }

  public HashMap<String, PackagingUnit> createPackagingUnitMap() {
    Map temp = Map.of(
        "tetrapak", new PackagingUnit("Tetrapak"),
        "sack", new PackagingUnit("Sack"),
        "palette", new PackagingUnit("Palette"),
        "fass", new PackagingUnit("Fass"),
        "kg", new PackagingUnit("kg")
    );
    HashMap<String, PackagingUnit> map = new HashMap<>();
    map.putAll(temp);
    return map;
  }

  public HashMap<String, Supplier> createSupplierMap() {
    Map temp = Map.of(
        "grissemann", new Supplier("Grissemann"),
        "wedl", new Supplier("C&C Wedl"),
        "fleischhof", new Supplier("Fleischhof Oberland")
    );
    HashMap<String, Supplier> map = new HashMap<>();
    map.putAll(temp);
    return map;
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
