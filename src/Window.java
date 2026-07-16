import javax.swing.JFrame;

public class Window extends JFrame{

    public Window(String title, Game game) {

        setTitle(title);
        add(game);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
