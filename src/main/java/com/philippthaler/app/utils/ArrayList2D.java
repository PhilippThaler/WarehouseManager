package com.philippthaler.utils;

import com.philippthaler.Position;

import java.util.ArrayList;

public class ArrayList2D<T> {

    private T[][] positions;
    private int rows;
    private int columns;

    public ArrayList2D() {
        positions=new T[0][0];
    }

    public ArrayList2D(int rows, int columns) {
        positions = new T[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    public void ensureRowCapacity(int size) {
        T[][] positions = new T[size][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                positions[i][j] = this.positions[i][j];
            }
        }
        this.positions = positions;
    }

    public void ensureColumnCapacity(int size) {
        T[][] positions = new T[rows][size];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                positions[i][j] = this.positions[i][j];
            }
        }
        this.positions = positions;
    }

    public void trimToSize() {

    }

}
