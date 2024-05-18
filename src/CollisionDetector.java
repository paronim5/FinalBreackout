import java.awt.*;

public class CollisionDetector {
    public static boolean checkCollision(Ball ball, Paddle paddle) {
        Rectangle ballRect = ball.hitBox();
        Rectangle paddleRect = paddle.hitBox();
        return ballRect.intersects(paddleRect);
    }

    public static boolean checkCollision(Ball ball, Block block) {
        Rectangle ballRect = ball.hitBox();
        Rectangle blockRect = block.hitBox();
        return ballRect.intersects(blockRect);
    }
}
