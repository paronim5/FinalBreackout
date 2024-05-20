import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GamePanel extends JPanel implements KeyListener {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    private Ball ball;
    private Paddle paddle;
    private List<Block> bricks;
    private Map<String, Integer> leaderboard = new HashMap<>();
    private Timer timer;
    private boolean inGame = true;

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

    public GamePanel() {

    }
    private void startGame() {}
    public static void createGameMenu() {}
    public void addScore(String player, int score){
        leaderboard.put(player, score);
    }




    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            paddle.moveLeft();
        } else if (key == KeyEvent.VK_D) {
            paddle.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_D) {
            paddle.stop();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            ball.draw(g);
            paddle.draw(g);
            for (Block brick : bricks) {
                brick.draw(g);
            }
        }
    }
}
