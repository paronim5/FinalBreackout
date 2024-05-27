/**
 * This class implements the {@link MovementStrategy} interface and defines the movement logic for a ball and a paddle.
 */
public class BPMovementStrategy implements MovementStrategy{
    /**
     * Moves the ball according to its speed and checks for collisions with walls.
     * If the ball is not moving, it initializes the ball speed and sets it equal to the movement.
     */
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

        }

    /**
     * Moves the paddle according to its current speed and checks for collisions with the walls.
     * Ensures the paddle stays within the bounds of the game panel.
     */
    @Override
    public void moveP(Paddle paddle) {
        paddle.setX(paddle.getX() + paddle.getDx()); // Use dx to move the paddle
        if (paddle.getX() < 0) {
            paddle.setX(0);
        }
        if (paddle.getX() + paddle.getWidth() > GamePanel.WIDTH) {
            paddle.setX(GamePanel.WIDTH - paddle.getWidth());
        }
    }
}
