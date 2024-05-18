import java.awt.*;

public class Block {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean destroyed;

    public Block(int x, int y, int width, int height, boolean destroyed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.destroyed = destroyed;
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

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
    //endregion
    public Rectangle hitBox(){
        return new Rectangle(x,y,width,height);
    }
    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillOval(x,y,width,height);
    }
}
