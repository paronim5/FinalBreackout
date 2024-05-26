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
    }
}