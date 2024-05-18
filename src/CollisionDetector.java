import java.awt.*;

public class CollisionDetector {
    public static boolean checkCollision(Ball ball, Paddle paddle) {
        Rectangle ballRect = ball.hitBox();
        Rectangle paddleRect = paddle.hitBox();
        return ballRect.intersects(paddleRect);
    }


}
