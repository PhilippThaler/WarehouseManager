package com.philippthaler.app.database;

import com.philippthaler.app.logic.Article;
import com.philippthaler.app.logic.PackagingUnit;
import com.philippthaler.app.utils.Price;
import com.philippthaler.app.logic.Supplier;

import java.util.Map;

public class Database<T> {

  private Map<String, Article> articles = createArticleMap();
  private Map<String, PackagingUnit> packagingUnits = createPackagingUnitMap();
  private Map<String, Supplier> suppliers = createSupplierMap();

  public Database() {

  }

  public Map<String, Article> createArticleMap() {
    Map<String, Article> articles = Map.of(
        "milch", new Article("Milch", "1", new Price(1, "€"), new Supplier("Grissemann"), new PackagingUnit("Tetrapak")),
        "mehl", new Article("Mehl", "2", new Price(2, "€"), suppliers.get("grissemann"),packagingUnits.get("sack")),
        "cola", new Article("Cola", "3", new Price(3,"€"), suppliers.get("wedl"), packagingUnits.get("palette"))
    );

    return articles;
  }

  public Map<String, PackagingUnit> createPackagingUnitMap() {
    Map<String, PackagingUnit> packagingUnits = Map.of(
        "tetrapak", new PackagingUnit("Tetrapak"),
        "sack", new PackagingUnit("Sack"),
        "palette", new PackagingUnit("Palette"),
        "fass", new PackagingUnit("Fass")
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


}
