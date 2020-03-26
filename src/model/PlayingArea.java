package model;

import java.awt.Color;
import java.awt.Graphics;
import static view.TetrisPanel.scores;

public class PlayingArea {
    
    private Cell playingArea[][];
    private Game game;

    public PlayingArea(Game game) {
        playingArea = new Cell[20][10];
        this.game = game;
    }

    public void pasteOnPlayingArea(int y, int x, Color color) {
        playingArea[y][x] = new Cell(color);
    }

    public void drawArea(Graphics g) {
        for (int y = 0; y < playingArea.length; y++) {
            for (int x = 0; x < playingArea[y].length; x++) {
                if (playingArea[y][x] != null) {
                    g.setColor(playingArea[y][x].getColor());
                    g.fillRect(x * Cell.CELL_SIZE, y * Cell.CELL_SIZE, Cell.CELL_SIZE - 1, Cell.CELL_SIZE - 1);
                }
            }
        }
    }

    public boolean canMakeStep(Figure currentFigure, int shiftY, int shiftX) {
        Cell[][] tempFigure = currentFigure.getCurrentFigure();
        int tempY = currentFigure.getY() + shiftY;
        int tempX = currentFigure.getX() + shiftX;

        for (int y = 0; y < tempFigure.length; y++) {
            for (int x = 0; x < tempFigure[y].length; x++) {
                if (tempFigure[y][x] != null) {
                    if (tempX + x < 0
                            || tempX + x >= 10
                            || tempY + y < 0
                            || tempY + y >= 20) {
                        return false;
                    }

                    if (playingArea[tempY + y][tempX + x] != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void gameover() {
        for (int x = 0; x < 10; x++) {
            if (playingArea[0][x] != null) {
                game.setStopped(true);
            }
        }
    }

    public void leaveFigure(Figure currentFigure) {
        Cell[][] tempFigure = currentFigure.getCurrentFigure();
        int tempY = currentFigure.getY();
        int tempX = currentFigure.getX();

        for (int y = 0; y < tempFigure.length; y++) {
            for (int x = 0; x < tempFigure[y].length; x++) {
                if (tempFigure[y][x] != null) {
                    pasteOnPlayingArea(y + tempY, x + tempX, tempFigure[y][x].getColor());
                }
            }
        }
        game.newFigure();
    }

    public void checkIfLineisFilled() {
        int counter = 0;
        for (int y = 0; y < playingArea.length; y++) {
            for (int x = 0; x < playingArea[y].length; x++) {
                if (playingArea[y][x] != null) {
                    counter++;
                }

                if (counter == 10) {
                    System.out.println("counter " + counter);
                    System.out.println("y line " + y);
                    clearLine(y);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    shiftOtherLines(y);
                }
            }
            counter = 0;
        }
    }

    private void clearLine(int y) {
        for (int i = 0; i < playingArea[y].length; i++) {
            playingArea[y][i] = null;
        }
        scores++;
    }

    public void shiftOtherLines(int startY) {
        for (int y = startY; y > 0; y--) {
            for (int x = 0; x < playingArea[y].length; x++) {
                //System.out.println("scores " + scores);
                playingArea[y][x] = playingArea[y - 1][x];
            }
        }
    }
}
