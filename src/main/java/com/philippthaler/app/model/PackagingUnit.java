package com.philippthaler.app.model;

/**
 * Implementation of Articleinfo.
 * Describes the packaging unit an article is packaged in
 */
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
