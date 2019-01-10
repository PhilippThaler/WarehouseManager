package com.philippthaler.app.model;

import com.philippthaler.app.utils.helpers.Price;

import java.util.HashMap;

/**
 * Implementation of the Article interface
 */
public class ArticleNormal implements Article {

  private String name;
  private String itemNumber;
  private Price price;
  private ArticleInfo supplier;
  private ArticleInfo packagingUnit;

  public ArticleNormal() {
    this("", new Price(-1), new Supplier(""), new PackagingUnit(""));
  }

  public ArticleNormal(String name) {
    this(name, new Price(-1), new Supplier(""), new PackagingUnit(""));
  }

  public ArticleNormal(String name, Price price, ArticleInfo supplier, ArticleInfo packagingUnit) {
    this.name = name;
    this.itemNumber = generateItemNumber(name);
    this.price = price;
    this.supplier = supplier;
    this.packagingUnit = packagingUnit;
  }

  public String getName() {
    return name;
  }

  public String getItemNumber() {
    return itemNumber;
  }

  public Price getPrice() {
    return price;
  }

  public ArticleInfo getSupplier() {
    return supplier;
  }

  public ArticleInfo getPackagingUnit() {
    return packagingUnit;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setItemNumber(String itemNumber) {
    this.itemNumber = itemNumber;
  }

  public void setPrice(Price price) {
    this.price = price;
  }

  public void setSupplier(ArticleInfo supplier) {
    this.supplier = supplier;
  }

  public void setPackagingUnit(ArticleInfo packagingUnit) {
    this.packagingUnit = packagingUnit;
  }

  private String generateItemNumber(String articleName) {
    return Math.abs(articleName.hashCode()) + "";
  }


  /**
   * Implementation of the equals method
   *
   * @param obj An object
   * @return Returns true, if every field of an ArticleInfo is equals to every field of another one.
   */
  @Override
  public boolean equals(Object obj) {
    return obj instanceof Article && ((ArticleNormal) obj).name.equals(name) && ((ArticleNormal) obj).itemNumber.equals(itemNumber) && ((ArticleNormal) obj).price.equals(price) && ((ArticleNormal) obj).price.equals(price);
  }

  @Override
  public String toString() {
    return "Article: " + name + ", Itemnumber: " + itemNumber + ", Price: " + price.getPrice();
  }
}
