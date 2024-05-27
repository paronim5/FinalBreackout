/**
 * Defines the behavior of movement strategies for game objects such as balls and paddle.
 */
public interface MovementStrategy {
    /**
     * Moves the specified ball according to the implemented strategy.
     */
    void move(Ball ball);
    /**
     * Moves the specified paddle according to the implemented strategy.
     */
    void moveP(Paddle paddle);
}
