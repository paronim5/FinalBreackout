import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
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
    private void startGame() {
        inGame = true;
        ball = new Ball(100, 100, 20, 2, 3, true);
        paddle = new Paddle(350, 500, 100, 20, 5);
        bricks = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            bricks.add(new Block(50 + (i % 10) * 70, 50 + (i / 10) * 30, 60, 20));
        }
        timer = new Timer(10, this);
        timer.start();
        requestFocus();
        removeAll();

        repaint();
    }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            ball.move();
            paddle.move();
            if (CollisionDetector.checkCollision(ball, paddle)) {
                ball.reverseY();
            }
            Iterator<Block> iterator = bricks.iterator();
            while (iterator.hasNext()) {
                Block brick = iterator.next();
                if (CollisionDetector.checkCollision(ball, brick)) {
                    ball.reverseY();
                    iterator.remove();
                }
            }
            // have to complete game over condition
            repaint();
        }
    }
}
