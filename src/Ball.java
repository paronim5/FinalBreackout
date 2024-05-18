import java.awt.*;

public class Ball {
    private int x;
    private int y;
    private int diameter;
    private int speedX;
    private int speedY;
    private boolean moving;

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
    public void move(){

        if (isMoving()) {
            x += speedX;
            y += speedY;
        }
    }
    public Rectangle hitBox() {
        return new Rectangle(x, y, diameter, diameter);
    }
}
