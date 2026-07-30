import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle {
    private int x;
    private int y;
    private final int width = 100;
    private final int height = 15;
    private final int speed = 10;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void keepInBounds(int panelWidth) {
        if (x < 0)
            x = 0;

        if (x + width > panelWidth)
            x = panelWidth - width;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}