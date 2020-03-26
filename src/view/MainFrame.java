package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame {
    public MainFrame(TetrisPanel tetrisPanel) {
        setName("Tetris by Igor");
        setSize(318, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(true);
        add(tetrisPanel);
        JLabel scoresLabel = new JLabel();
        tetrisPanel.setLable(scoresLabel);
        //jlbl.setText(String.valueOf("Scores "  + TetrisPanel.scores));
        scoresLabel.setText("Scores ");
        add(scoresLabel, BorderLayout.NORTH);
        addKeyListener(tetrisPanel);
        setVisible(true);
    }
}
