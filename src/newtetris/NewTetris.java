package newtetris;

import javax.swing.SwingUtilities;
import view.MainFrame;
import view.TetrisPanel;

public class NewTetris {

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(new TetrisPanel());
        });
    }   
}
