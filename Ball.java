import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {
    private double x, y;
    private double dx, dy;
    private final int diameter = 16;

    public Ball(double x, double y, double speed) {
        this.x = x;
        this.y = y;
        this.dx = speed;
        this.dy = -speed;
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval((int) x, (int) y, diameter, diameter);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, diameter, diameter);
    }

    public void reverseX() { dx = -dx; }
    public void reverseY() { dy = -dy; }

    public double getX() { return x; }
    public double getY() { return y; }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public int getDiameter() { return diameter; }

    public void reset(double x, double y, double speed) {
        this.x = x;
        this.y = y;
        this.dx = speed;
        this.dy = -speed;
    }
}