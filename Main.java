import javax.swing.*;

public class Main {

    static JFrame J = new JFrame("PADDLE POWER");

    public static void main(String[] args) {

        J.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        J.setSize(660, 670);

        Pong game = new Pong();
        J.add(game);

        J.setVisible(true);

        Timer ballDelay = new Timer(33, e -> {

            game.gameLogic();
            game.repaint();
        });

        ballDelay.start();

    }
}