package com.philippthaler.app;

public class PackagingUnit {

  private String unit;

  public PackagingUnit(String unit) {
    this.unit = unit;
  }

  public String getUnit() {
    return unit;
  }

  @Override
  public String toString() {
    return unit;
  }
}
