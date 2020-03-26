package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Figure {

    private static Random r = new Random(System.currentTimeMillis());
    
    private static Color[] colors = {
        Color.BLUE,
        Color.CYAN,
        Color.ORANGE,
        Color.RED,
        Color.MAGENTA,
        Color.GREEN,
        Color.YELLOW,
    };
    
    private static int[][][] tempFigure = {
        {
            {0, 0, 0, 0}, // O
            {0, 1, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}},
        {
            {0, 0, 0, 0}, // S
            {0, 0, 1, 1},
            {0, 1, 1, 0},
            {0, 0, 0, 0}
        },
        {
            {0, 1, 0, 0}, // I
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0}},
        {
            {0, 0, 1, 0}, // J
            {0, 0, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}},
        {
            {0, 0, 0, 0}, // T
            {0, 1, 1, 1},
            {0, 0, 1, 0},
            {0, 0, 0, 0}
        },
        {
            {0, 0, 0, 0}, // Z
            {0, 1, 1, 0},
            {0, 0, 1, 1},
            {0, 0, 0, 0}
        }, {
            {0, 1, 0, 0}, // L
            {0, 1, 0, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}}
    };

    private int x;
    private int y;
    private Color color;
    private Cell[][] grid;

    private Figure(int x, int y, Color color, Cell[][] grid) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.grid = grid;
    }
    
    public static Figure newInstance() {
        int index = r.nextInt(tempFigure.length);
        
        int x = 4; //begin x
        int y = 0; // begin y
        Color color = colors[index];
        Cell[][] grid = createGrid(index, color);
        
        return new Figure(x, y, color, grid);
    }

    private static Cell[][] createGrid(int index, Color color) {
        Cell[][] figure = new Cell[Cell.ARRAY_BORDER][Cell.ARRAY_BORDER];
        for (int y = 0; y < Cell.ARRAY_BORDER; y++) {
            for (int x = 0; x < Cell.ARRAY_BORDER; x++) {
                if (tempFigure[index][y][x] == 0) {
                    continue;
                }

                figure[y][x] = new Cell(color);
            }
        }
        return figure;
    }

    public Color getColor() {
        return color;
    }

    

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Cell[][] getCurrentFigure() {
        return grid;
    }

    /*public void leaveFigure() {
        generateFigure();
    }*/

    /*@Deprecated
    public void moveAside(Direction direction) {
        switch (direction) {
            case RIGHT:
                right();
                break;
            case LEFT:
                left();
                break;
            case DEFAULT:
                break;
        }
    }*/

    public void right() {
        x++;
    }

    public void left() {
        x--;
    }

    public void moveDown() {
        y++;
    }

    public void drawFigure(Graphics g) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[j][i] != null) {
                    g.fillRect(x * Cell.CELL_SIZE + i * Cell.CELL_SIZE, y * Cell.CELL_SIZE + j * Cell.CELL_SIZE, Cell.CELL_SIZE - 1, Cell.CELL_SIZE - 1);
                    //System.out.println("Painted " + x + " " + y);
                }
            }
        }
    }

    public void turnFigure() {
        Cell[][] tempFigure = new Cell[Cell.ARRAY_BORDER][Cell.ARRAY_BORDER];
        for (int y = 0; y < Cell.ARRAY_BORDER; y++) {
            for (int x = 0; x < Cell.ARRAY_BORDER; x++) {
                //tempFigure[y][x] = currentFigure[x][y];
                tempFigure[y][x] = grid[grid.length - x - 1][y];
                if (y + this.y >= 20 || x + this.x <= -1 || x + this.x >= 10) {
                    System.out.println("more than we need");
                    return;
                }
            }
        }
        grid = tempFigure;
    }
}
