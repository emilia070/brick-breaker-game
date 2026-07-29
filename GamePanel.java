import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {

    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 600;
    private static final int FPS = 60;

    private int paddleX = 350;
    private int paddleY = 550;
    private int paddleWidth = 100;
    private int paddleHeight = 15;
    private int paddleSpeed = 10;

    private boolean movingLeft = false;
    private boolean movingRight = false;

    private Ball ball;

    public GamePanel() {
        this.setPreferredSize(new java.awt.Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.pink);
        this.setFocusable(true);

        ball = new Ball(PANEL_WIDTH / 2.0, PANEL_HEIGHT / 2.5, 5);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) movingLeft = true;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) movingRight = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) movingLeft = false;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) movingRight = false;
            }
        });

        Timer timer = new Timer(1000 / FPS, e -> {
            update();
            repaint();
        });
        timer.start();
    }

    private void update() {
        if (movingLeft) paddleX -= paddleSpeed;
        if (movingRight) paddleX += paddleSpeed;
        if (paddleX < 0) paddleX = 0;
        if (paddleX + paddleWidth > PANEL_WIDTH) paddleX = PANEL_WIDTH - paddleWidth;

        ball.update();

        // bounce off left/right walls
        if (ball.getX() <= 0 || ball.getX() + ball.getDiameter() >= PANEL_WIDTH) {
            ball.reverseX();
        }
        // bounce off the top wall
        if (ball.getY() <= 0) {
            ball.reverseY();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(paddleX, paddleY, paddleWidth, paddleHeight);
        ball.draw(g);
    }
}