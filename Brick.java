import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Brick {
    private int x;
    private int y;

    private final int width = 60;
    private final int height = 20;

    private boolean destroyed = false;

    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        if (!destroyed) {
            g.setColor(Color.white);
            g.fillRect(x, y, width, height);

            g.setColor(Color.black);
            g.drawRect(x, y, width, height);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}