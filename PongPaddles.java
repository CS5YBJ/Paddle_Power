import java.awt.*;

public class PongPaddles {

    private final int height;
    private final int x;
    private int y;
    private final int speed;
    private final Color color;

    static final int PADDLE_WIDTH = 15;

    public PongPaddles(int x, int y, int height, int speed, Color color) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.speed = speed;
        this.color = color;
    }

    public void paint(Graphics g) {

        g.setColor(color);

        g.fillRect(x, y, PADDLE_WIDTH, height);

    }

    public void moveTowards(int moveToY) {

        int centerY = y + height / 2;

        if (Math.abs(centerY - moveToY) > speed) {

            if (centerY > moveToY) {

                y -= speed;
            }

            if (centerY < moveToY) {

                y += speed;
            }
        }

    }

    public boolean checkCollision(PongBall b) {

        int rightX = x + PADDLE_WIDTH;
        int bottomY = y + height;

        if (b.getX() > (x - b.getBallSize()) && b.getX() < rightX) {

            return b.getY() > y && b.getY() < bottomY;
        }

        return false;

    }

    public int getX() {
        return x;
    }

    public int getCenterY() {
        return y + height / 2;
    }

}