import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePanel extends JPanel implements KeyListener {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    private Ball ball;
    private Paddle paddle;
    private List<Block> bricks;
    private Map<String, Integer> leaderboard = new HashMap<>();

//region getters and setters
    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    public List<Block> getBricks() {
        return bricks;
    }

    public void setBricks(List<Block> bricks) {
        this.bricks = bricks;
    }
    //endregion

    public GamePanel() {}
    private void startGame() {}
    public static void createGameMenu() {}
    public void addScore(){}


    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {}
}
