import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Pong extends JPanel implements MouseMotionListener {
    static final int WINDOW_WIDTH = 670, WINDOW_HEIGHT = 670;
    private PongBall Ball, computerBall;
    private PongPaddles playerPaddle, computerPaddle;
    private int playerPoints, computerPoints;
    private int playerMouseY;
    private int bounceCounter;
    private int YCollisionDetected;
    private boolean computerToBall;
    private int oscillate;
    private boolean computerForcedMiss;
    public Pong() {

        Ball = new PongBall(300, 200, 3, 3, 5, Color.WHITE, 10);
        computerBall = new PongBall(Ball);
        playerPaddle = new PongPaddles(10, 200, 75, 5, Color.MAGENTA);
        computerPaddle = new PongPaddles(610, 200, 75, 3, Color.GREEN);

        playerMouseY = 0;
        playerPoints = 0;
        computerPoints = 0;
        bounceCounter = 0;

        YCollisionDetected = -1;
        computerToBall = false;
        oscillate = 0;
        computerForcedMiss = false;

        addMouseMotionListener(this);
    }
    public void reset() {

        Ball = new PongBall(320, 220, 3, 3, 5, Color.WHITE, 10);
        playerPaddle = new PongPaddles(10, 200, 75,5, Color.MAGENTA);
        computerPaddle = new PongPaddles(610, 200, 75, 3, Color.GREEN);
        bounceCounter = 0;
        computerForcedMiss = false;

        computerBall = new PongBall(Ball);
        YCollisionDetected = -1;
        computerToBall = false;
    }

    public void paintComponent(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        Ball.paint(g);

        playerPaddle.paint(g);
        computerPaddle.paint(g);

        g.setColor(Color.WHITE);

        g.drawString("Score - User [ " + playerPoints + " ]   PC [ " + computerPoints + " ]", 250, 20);
    }

    public void gameLogic() {

        Ball.moveBall();

        Ball.bounceOffEdges(0, WINDOW_HEIGHT);

        if (YCollisionDetected == -1) {

            for (int i = 0; i < 10; i++) {

                computerBall.moveBall();
                computerBall.bounceOffEdges(0, WINDOW_HEIGHT);

                if (computerBall.getX() < playerPaddle.getX() + PongPaddles.PADDLE_WIDTH) {
                    computerBall.reverseX();
                }

                if (computerBall.getX() > computerPaddle.getX()) {
                    YCollisionDetected = computerBall.getY();

                    if (computerForcedMiss) {
                        YCollisionDetected += 75;
                    }

                    break;
                }
            }
        }

        if ((Math.abs(computerPaddle.getCenterY() - YCollisionDetected) < 3) && !computerToBall) {
            computerToBall = true;
        }

        if (!computerToBall) {

            computerPaddle.moveTowards(YCollisionDetected);
        } else {

            if (computerPaddle.getCenterY() > YCollisionDetected + 10) {

                oscillate = 0;
            } else if (computerPaddle.getCenterY() < YCollisionDetected - 10) {

                oscillate = WINDOW_HEIGHT;
            }

            computerPaddle.moveTowards(oscillate);
        }

        playerPaddle.moveTowards(playerMouseY);

        if (playerPaddle.checkCollision(Ball)) {
            Ball.reverseX();

            Ball.setX(playerPaddle.getX() + PongPaddles.PADDLE_WIDTH + 1);
            bounceCounter++;
        }

        if (computerPaddle.checkCollision(Ball)) {
            Ball.reverseX();

            Ball.setX(computerPaddle.getX() - 10);

            computerBall = new PongBall(Ball);

            YCollisionDetected = -1;

            computerToBall = false;
            bounceCounter++;

            if ((int)(Math.random() * 5) == 0) {
                computerForcedMiss = true;
            }
        }

        if (bounceCounter == 1) {

            bounceCounter = 0;

            Ball.increaseSpeed();
        }

        if (Ball.getX() < 0) {

            computerPoints++;
            reset();
        } else if (Ball.getX() > WINDOW_WIDTH) {

            playerPoints++;
            reset();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        playerMouseY = e.getY();
    }
}