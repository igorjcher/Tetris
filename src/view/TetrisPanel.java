package view;

import model.Game;
import model.PlayingArea;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Figure;

public class TetrisPanel extends JPanel implements KeyListener, ActionListener, Game {

    private static final int DELAY = 200;
    
    private PlayingArea playingArea;
    private Figure figure;
    private Timer timer;
    private boolean isStopped;
    private boolean pause = false;
    public static int scores;
    public static JLabel scoresLabel = new JLabel();

    public TetrisPanel() {
        initGame();
    }

    @Override
    public boolean isStopped() {
        return isStopped;
    }

    @Override
    public void setStopped(boolean flag) {
        isStopped = flag;
    }

    @Override
    public void newFigure() {
        figure = Figure.newInstance();
    }

    public void initGame() {
        scores = 0;
        isStopped = false;
        figure = Figure.newInstance();
        setBackground(Color.DARK_GRAY);
        playingArea = new PlayingArea(this);
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void setLable(JLabel scoresLabel) {
        this.scoresLabel = scoresLabel;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        playingArea.drawArea(g);
        g.setColor(((Figure) figure).getColor());
        figure.drawFigure(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moveRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            turnFigure();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            dropFigure();
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            pause();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStopped) {
            timer.stop();
            restartGame();
        } else {
            try {
                Thread.sleep(DELAY);
                move();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void moveRight() {
        if (playingArea.canMakeStep(figure, 0, 1)) {
            figure.right();
            repaint();
        }
    }

    public void moveLeft() {
        if (playingArea.canMakeStep(figure, 0, -1)) {
            figure.left();
            repaint();
        }
    }

    public void moveDown() {

        if (pause) {
            return;
        }

        if (playingArea.canMakeStep(figure, 1, 0)) {
            figure.moveDown();
            playingArea.gameover();
            playingArea.checkIfLineisFilled();
            repaint();
        } else {
            playingArea.gameover();
            playingArea.leaveFigure(figure);
            playingArea.checkIfLineisFilled();
            scoresLabel.setText("Scores: " + scores);
            repaint();
        }
    }

    public void dropFigure() {
        if (pause) {
            return;
        }

        while (playingArea.canMakeStep(figure, 1, 0)) {
            figure.moveDown();
        }

        playingArea.leaveFigure(figure);
        playingArea.checkIfLineisFilled();
        scoresLabel.setText("Scores: " + scores);
        repaint();
    }

    public void restartGame() {
        initGame();
    }

    public void turnFigure() {
        figure.turnFigure();
    }

    public void pause() {
        pause = !pause;
        if (pause == true) {
            scoresLabel.setText("Paused");
        } else {
            scoresLabel.setText("Scores: " + scores);
        }
        repaint();
    }

    private void move() {
        moveDown();
    }
}
