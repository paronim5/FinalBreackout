import java.awt.*;
import java.util.Random;

public class Ball {
    private int x;
    private int y;
    private int diameter;
    private int speedX;
    private int speedY;
    private boolean moving;

    /**
     * Constructor for a new ball with parameters.
     *
     * @param x the original x-coordinate
     * @param y the original y-coordinate
     * @param diameter the diameter of the ball
     * @param speedX the initial velocity along the x-axis
     * @param speedY the initial velocity along the y-axis
     * @param moving the initial moving state of the ball
     */
    public Ball(int x, int y, int diameter, int speedX, int speedY, boolean moving) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.speedX = speedX;
        this.speedY = speedY;
        this.moving = moving;
    }

//region getters and setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    //endregion


    /**
     * Method for creating the hitbox of the ball.
     */
    public Rectangle hitBox() {
        return new Rectangle(x, y, diameter, diameter);
    }
    /**
     * Changes the x-axis velocity.
     */
    public void reverseX() {
        speedX = -speedX;
    }
    /**
     * Changes the y-axis velocity.
     */
    public void reverseY() {
        speedY = -speedY;
    }
    /**
     * Draws the ball.
     */
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, diameter, diameter);
    }
    /**
     * Increases the speed of the ball to random values between 1 and 4.
     */
    public void increaseSpeed() {
        Random rand = new Random();
        int randomSpeedX = rand.nextInt(4) + 1;
        int randomSpeedY = rand.nextInt(4) + 1;

        speedX = randomSpeedX;
        speedY = randomSpeedY;
        System.out.println("speed has increased " + speedX+" "+ speedY);
    }
}
