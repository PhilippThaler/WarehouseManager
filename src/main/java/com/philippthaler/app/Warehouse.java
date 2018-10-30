package com.philippthaler.app;

import com.philippthaler.app.utils.Position2DArray;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private Position2DArray articleArray;

    public Warehouse(int columns, int rows) {
      articleArray = new Position2DArray(columns, rows);
    }

}
