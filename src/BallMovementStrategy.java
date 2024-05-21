import java.util.List;

public class  BallMovementStrategy implements MovementStrategy{
    private GamePanel gm;

    @Override
    public void move(Ball ball) {
        if (!ball.isMoving()) {
            ball.setSpeedX(1); // started speed x
            ball.setSpeedY(-1); // started speed y
            ball.setMoving(true);
        }

        // Ball movement
        ball.setX(ball.getX() + ball.getSpeedX());
        ball.setY(ball.getY() + ball.getSpeedY());

        // Collision with walls
        if (ball.getX() + ball.getDiameter() >= GamePanel.WIDTH || ball.getX() <= 0) {
            ball.reverseX(); // Reflect from right and left wall
        }
        if (ball.getY() <= 0) {
            ball.reverseY(); // Reflect from top
        }

        List<Block> blocks = gm.getBricks(); // Get blocks from game panel
        for (Block block : blocks) {
            if (CollisionDetector.checkCollision(ball, block)) {
                // Change ball direction
                ball.reverseY();
                blocks.remove(block); // Remove block from list
                break; // Break loop to not check collision with more than one block
            }
        }
    }

    @Override
    public void moveP(Paddle paddle) {

    }
}
