package com.philippthaler.app;

public class Article {

  private String name;
  private String itemNumber;
  private Price price;
  private Supplier supplier;
  private PackagingUnit packagingUnit;

  public Article(String name, String itemNumber, Price price, Supplier supplier, PackagingUnit packagingUnit) {
    this.name = name;
    this.itemNumber = itemNumber;
    this.price=price;
    this.supplier=supplier;
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

  public Supplier getSupplier() {
    return supplier;
  }

  public PackagingUnit getPackagingUnit() {
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

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }

  public void setPackagingUnit(PackagingUnit packagingUnit) {
    this.packagingUnit = packagingUnit;
  }
}
