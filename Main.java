
import javax.swing.*;

/**
 * Created by Igor Gridin on 05.05.17.
 * <p>
 * Main class runs the game.
 **/

public class Main {
    static JFrame frame;

    private void GUI() {
        frame = new JFrame("Classic Snake");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.isAlwaysOnTop();
        frame.setResizable(false);
        frame.add(new MainMenuGUI());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.requestFocus();
    }

    public static void main(String[] args) {
        new Main().GUI();
    }
}

