public class BPMovementStrategy implements MovementStrategy{
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

        }


    @Override
    public void moveP(Paddle paddle) {
        paddle.setX(paddle.getX() + paddle.getDx()); // use dx to move the paddle
        if (paddle.getX() < 0) {
            paddle.setX(0);
        }
        if (paddle.getX() + paddle.getWidth() > GamePanel.WIDTH) {
            paddle.setX(GamePanel.WIDTH - paddle.getWidth());
        }
    }
}
