import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public static boolean leftPressed;
    public static boolean rightPressed;

    public KeyHandler() {
        leftPressed = false;
        rightPressed = false;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_A) {
            leftPressed = true;
        } else if (keyCode == KeyEvent.VK_D) {
            rightPressed = true;

    }
    }

    @Override
    public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_A) {
                leftPressed = false;
            } else if (keyCode == KeyEvent.VK_D) {
                rightPressed = false;
    }
    }
}
