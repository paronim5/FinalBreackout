import java.awt.*;

public class Block {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean destroyed;
    /**
     * Constructor for a new block with parameters.
     *
     * @param x the original x-coordinate
     * @param y the original y-coordinate
     * @param width the width of the block
     * @param height the height of the block
     */

    public Block(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.destroyed = false;
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
    /**
     * Method for creating the hitbox of the block.
     */
    public Rectangle hitBox(){
        return new Rectangle(x,y,width,height);
    }
    /**
     * Draws the block.
     */
    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillRect(x,y,width,height);
    }
}
