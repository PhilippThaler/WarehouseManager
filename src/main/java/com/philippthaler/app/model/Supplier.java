package com.philippthaler.app.model;

/**
 * Implementation of Articleinfo.
 * Describes the supplier that ships articles.
 */

public class Supplier implements ArticleInfo, Comparable<Supplier> {

  private String name;

  public Supplier(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Supplier && ((Supplier) obj).name.equals(name);
  }

  @Override
  public int compareTo(Supplier supplier) {
    return name.compareTo(supplier.name);
  }

  @Override
  public String toString() {
    return "Supplier: " + name;
  }
}
