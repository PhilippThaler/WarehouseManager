package com.philippthaler.app.database;

import com.philippthaler.app.PackagingUnit;

import java.util.Map;

public class PackagingUnitDatabase extends Database {
  private Map<String, PackagingUnit> packagingUnits = createDatabase();

  public PackagingUnitDatabase() {

  }

  Map<String, PackagingUnit> createDatabase() {
    Map<String, PackagingUnit> packagingUnits = Map.of(
        "tetrapak", new PackagingUnit("Tetrapak"),
        "sack", new PackagingUnit("Sack"),
        "palette", new PackagingUnit("Palette"),
        "fass", new PackagingUnit("Fass")
    );

    return packagingUnits;
  }
}
