import javax.swing.JFrame;

public class GameFrame extends JFrame{
    public GameFrame(){
        GamePanel panel = new GamePanel();
        this.add(panel);

        this.setTitle("Brick Breaker");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

}
