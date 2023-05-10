import java.awt.*;

public class PongBall {

    static final int MAX_SPEED = 20;

    private int x;
    private int y;
    private int mx;
    private int my;
    private int speed;
    private final int ballSize;
    private final Color ballColour;

    public PongBall(int x, int y, int mx, int my, int speed, Color ballColour, int ballSize) {
        this.x = x;
        this.y = y;
        this.mx = mx;
        this.my = my;
        this.speed = speed;
        this.ballColour = ballColour;
        this.ballSize = ballSize;
    }

    public PongBall(PongBall b) {
        this.x = b.x;
        this.y = b.y;
        this.mx = b.mx;
        this.my = b.my;
        this.speed = b.speed;
        this.ballColour = Color.WHITE;
        this.ballSize = b.ballSize;
    }

    public void paint(Graphics g) {

        g.setColor(ballColour);

        g.fillOval(x, y, ballSize, ballSize);

    }

    public void moveBall() {
        x += mx;
        y += my;
    }

    public void bounceOffEdges(int top, int bottom) {

        if (y > bottom - ballSize) {
            reverseY();
        } else if (y < top) {
            reverseY();
        }

    }

    public void reverseX() {
        mx *= -1;
    }

    public void reverseY() {
        my *= -1;
    }

    public void increaseSpeed() {

        if (speed < MAX_SPEED) {

            speed++;

            mx = (mx / Math.abs(mx) * speed);
            my = (my / Math.abs(my) * speed);

        }
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getBallSize() {
        return ballSize;
    }

}