import java.awt.*;

public class Paddle {
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed;
    private int dx;

    public Paddle(int x, int y, int width, int height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.dx = 0;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }
    //endregion
    public void move() {
        x += dx;
        if (x < 0) {
            x = 0;
        } else if (x + width > GamePanel.WIDTH) {
            x = GamePanel.WIDTH - width;
        }
    }
    public Rectangle hitBox() {
        return new Rectangle(x, y, width, height);
    }
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }
}
