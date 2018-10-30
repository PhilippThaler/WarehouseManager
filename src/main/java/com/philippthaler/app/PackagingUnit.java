package com.philippthaler.app;

public class PackagingUnit implements ArticleInfo {

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
}
