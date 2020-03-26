package model;

public interface Game {
    boolean isStopped();
    void setStopped(boolean flag);
    void newFigure();
}
