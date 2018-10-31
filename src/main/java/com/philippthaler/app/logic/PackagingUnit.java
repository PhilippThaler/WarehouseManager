package com.philippthaler.app.logic;

public class PackagingUnit implements ArticleInfo, Comparable<PackagingUnit> {

  private String name;

  public PackagingUnit(String unit) {
    this.name = unit;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }


  @Override
  public int compareTo(PackagingUnit packagingUnit) {
    return name.compareTo(packagingUnit.name);
  }
}
