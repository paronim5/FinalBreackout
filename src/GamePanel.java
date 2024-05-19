import javax.swing.*;
import java.util.List;

public class GamePanel extends JPanel {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    private Ball ball;
    private Paddle paddle;
    private List<Block> bricks;

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


}
