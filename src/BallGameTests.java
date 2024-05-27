import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BallGameTests {
    private Ball ball;
    private Paddle paddle;
    private Block block;

    @BeforeEach
    void setUp() {
        ball = new Ball(100, 100, 20, 2, 2, true);
        paddle = new Paddle(95, 150, 50, 10, 5);
        block = new Block(100, 50, 30, 10);
    }

    @Test
    void reverseX() {
        ball.reverseX();
        // check that the x direction has reversed
        assertEquals(-2, ball.getSpeedX());
    }

    @Test
    void reverseY() {
        ball.reverseY();
        // check that the y direction has reversed
        assertEquals(-2,ball.getSpeedY());
    }
    @Test
    public void testBallPaddleCollision() {
        ball.setX(100);
        ball.setY(100);
        paddle.setX(95);
        paddle.setY(110);
        assertTrue(CollisionDetector.checkCollision(ball, paddle));
    }
    @Test
    public void testBallBlockCollision() {
        ball.setX(200);
        block.setX(190);
        ball.setY(200);
        block.setY(210);
        assertTrue(CollisionDetector.checkCollision(ball, block));
    }
    @Test
    public void testBallReflectsFromPaddle() {
        ball.setX(paddle.getX() + paddle.getWidth() / 2);
        ball.setY(paddle.getY() - ball.getDiameter());
        ball.setSpeedY(2);

        new BPMovementStrategy().move(ball);
        if (CollisionDetector.checkCollision(ball, paddle)) {
            ball.reverseY();
        }
        assertEquals(-2, ball.getSpeedY());
    }
    @Test
    public void testBallReflectsOnWall() {
        int originalSpeedX = ball.getSpeedX();
        ball.setX(GamePanel.WIDTH - ball.getDiameter() - 1);
        new BPMovementStrategy().move(ball);
        assertEquals(-originalSpeedX, ball.getSpeedX());
    }

}
