package com.philippthaler;

public class PackagingUnit {

    private String unit;

    public PackagingUnit() {
        unit = "";
    }

    public PackagingUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return unit;
    }
}
