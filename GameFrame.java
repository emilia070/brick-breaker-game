import javax.swing.JFrame;

public class GameFrame extends JFrame{
    public GameFrame(){
        this.setTitle("Brick Breaker");
        this.setSize(800, 700);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

}
