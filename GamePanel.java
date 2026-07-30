import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {

    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 600;
    private static final int FPS = 60;

    private Ball ball;
    private Paddle paddle;
    private ArrayList<Brick> bricks;

    private boolean movingLeft;
    private boolean movingRight;

    private int score = 0;
    private int lives = 3;

    private boolean gameOver = false;
    private boolean gameWon = false;
    private boolean ballLaunched = false;

    public GamePanel() {

        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.PINK);
        setFocusable(true);

        ball = new Ball(PANEL_WIDTH / 2.0, PANEL_HEIGHT / 2.5, 5);
        paddle = new Paddle(350, 550);

        bricks = new ArrayList<>();

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 10; col++) {
                bricks.add(new Brick(60 + col * 70, 50 + row * 30));
            }
        }

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    movingLeft = true;
                }

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    movingRight = true;
                }

                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    ballLaunched = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                    movingLeft = false;

                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                    movingRight = false;
            }

        });

        Timer timer = new Timer(1000 / FPS, e -> {
            update();
            repaint();
        });

        timer.start();

        requestFocus();
    }

    private void update() {

        if (gameOver || gameWon)
            return;

        if (movingLeft)
            paddle.moveLeft();

        if (movingRight)
            paddle.moveRight();

        paddle.keepInBounds(PANEL_WIDTH);

        if (!ballLaunched) {
            ball.setX(paddle.getBounds().x + paddle.getBounds().width / 2.0 - ball.getDiameter() / 2.0);
            ball.setY(paddle.getBounds().y - ball.getDiameter());
            return;
        }

        ball.update();

        if (ball.getBounds().intersects(paddle.getBounds())) {

            ball.setY(paddle.getBounds().y - ball.getDiameter());
            ball.reverseY();

        }

        if (ball.getX() <= 0 ||
                ball.getX() + ball.getDiameter() >= PANEL_WIDTH) {

            ball.reverseX();

        }

        if (ball.getY() <= 0) {

            ball.reverseY();

        }

        for (Brick brick : bricks) {

            if (!brick.isDestroyed()
                    && ball.getBounds().intersects(brick.getBounds())) {

                brick.setDestroyed(true);

                score++;

                ball.reverseY();

                break;

            }

        }

        if (ball.getY() > PANEL_HEIGHT) {

            lives--;

            if (lives <= 0) {

                gameOver = true;

            } else {

                ball.reset(PANEL_WIDTH / 2.0, PANEL_HEIGHT / 2.5, 5);
                ballLaunched = false;

            }

        }

        gameWon = true;

        for (Brick brick : bricks) {

            if (!brick.isDestroyed()) {

                gameWon = false;
                break;

            }

        }

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.setColor(Color.WHITE);

        g.setFont(new Font("Arial", Font.BOLD, 18));

        g.drawString("Score: " + score, 20, 25);

        g.drawString("Lives: " + lives, 690, 25);

        if (!ballLaunched && !gameOver && !gameWon) {

            g.setFont(new Font("Arial", Font.BOLD, 22));
            g.drawString("Welcome to Brick Breaker!", 250, 260);

            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Press SPACE to launch the game & use LEFT and RIGHT arrows to MOVE the ball.", 230, 285);

        }

        for (Brick brick : bricks) {

            brick.draw(g);

        }

        paddle.draw(g);

        ball.draw(g);

        if (gameOver) {

            g.setColor(Color.RED);

            g.setFont(new Font("Arial", Font.BOLD, 40));

            g.drawString("GAME OVER", 250, 340);

        }

        if (gameWon) {

            g.setColor(Color.GREEN);

            g.setFont(new Font("Arial", Font.BOLD, 40));

            g.drawString("YOU WIN!", 270, 340);

        }

    }

}