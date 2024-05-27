import java.awt.*;
/**
 * Class for detecting collisions between game objects such as balls, paddles, and blocks.
 */
public class CollisionDetector {
    /**
     * Checks if there is a collision between the given ball and paddle.
     *
     * @param ball the ball whose collision with the paddle we are checking
     * @param paddle the paddle whose collision with the ball we are checking
     * @return {@code true} if the ball and the paddle intersect, {@code false} otherwise
     */
    public static boolean checkCollision(Ball ball, Paddle paddle) {
        Rectangle ballRect = ball.hitBox();
        Rectangle paddleRect = paddle.hitBox();
        return ballRect.intersects(paddleRect);
    }
    /**
     * Checks if there is a collision between the given ball and block.
     *
     * @param ball the ball whose collision with the block we are checking
     * @param block the block whose collision with the ball we are checking
     * @return {@code true} if the ball and the block intersect, {@code false} otherwise
     */
    public static boolean checkCollision(Ball ball, Block block) {
        Rectangle ballRect = ball.hitBox();
        Rectangle blockRect = block.hitBox();
        return ballRect.intersects(blockRect);
    }
}
