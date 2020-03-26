package model;

import java.awt.Color;

public class Cell {
    public static final int ARRAY_BORDER = 4;
    public static final int CELL_SIZE = 30;

    private Color color;
    
    public Cell(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
