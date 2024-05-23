import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    public static final int WIDTH = 900;
    public static final int HEIGHT = 800;
    private Ball ball;
    private Paddle paddle;
    private List<Block> bricks;

    private Timer timer;
    private boolean inGame = true;

//region getters and setters
    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    public List<Block> getBricks() {
        return bricks;
    }

    public void setBricks(List<Block> bricks) {
        this.bricks = bricks;
    }
    //endregion

    public GamePanel() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addKeyListener(this);
        startGame();
    }
    private void startGame() {
        inGame = true;
        ball = new Ball(260, 400, 20, 2, 3, true);
        paddle = new Paddle(300, 500, 100, 20, 5);
        bricks = new ArrayList<>();
        int numBricksPerRow = 16;
        int numRows = 4;
        int margin  =3;
        int brickWidth = (GamePanel.WIDTH - (margin  * (numBricksPerRow + 1))) / numBricksPerRow;
        int brickHeight = 20;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numBricksPerRow; col++) {
                int x = col * (brickWidth + margin ) + margin ;
                int y = row * (brickHeight + margin ) + 50;
                bricks.add(new Block(x, y, brickWidth, brickHeight));
            }
        }
        timer = new Timer(5, this);
        timer.start();
        requestFocus();
        removeAll();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            paddle.moveLeft();
        } else if (key == KeyEvent.VK_D) {
            paddle.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_D) {
            paddle.stop();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            ball.draw(g);
            paddle.draw(g);
            for (Block brick : bricks) {
                brick.draw(g);
            }
        } else {
            String message;
            if (bricks.isEmpty()) {
                message = "You Win!";
            } else {
                message = "Game Over";
            }
            Font font = new Font("Helvetica", Font.BOLD, 40);
            FontMetrics metrics = getFontMetrics(font);
            g.setColor(Color.RED);
            g.setFont(font);
            int y = HEIGHT / 2;
            for (String line : message.split("\n")) {
                g.drawString(line, (WIDTH - metrics.stringWidth(line)) / 2, y);
                y += metrics.getHeight();
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            ball.move();
            paddle.move();
            if (CollisionDetector.checkCollision(ball, paddle)) {
                ball.reverseY();
            }
            Iterator<Block> iterator = bricks.iterator();
            while (iterator.hasNext()) {
                Block brick = iterator.next();
                if (CollisionDetector.checkCollision(ball, brick)) {
                    ball.reverseY();
                    iterator.remove();
                }
            }
            //game over
            if (ball.getY() + ball.getDiameter() > HEIGHT) {
                inGame = false;
                timer.stop();
            }//win
            if (bricks.isEmpty()) {
                inGame = false;
                timer.stop();
            }
            repaint();
        }
    }

    public static void createGameMenu() {
        JFrame frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel controlPanel = new JPanel();
        JButton startButton = new JButton("Start");
        JButton exitButton = new JButton("Exit");
        JButton tutorialButton = new JButton("Tutorial");

        controlPanel.add(startButton);
        controlPanel.add(tutorialButton);
        controlPanel.add(exitButton);

        frame.add(controlPanel);
        frame.setVisible(true);

        exitButton.addActionListener(e -> System.exit(0));

        startButton.addActionListener(e -> {
            JFrame gameFrame = new JFrame("Game Field");
            GamePanel gamePanel = new GamePanel();
            gameFrame.add(gamePanel);
            gameFrame.pack();
            gameFrame.setResizable(false);
            gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            gameFrame.setVisible(true);
        });
        tutorialButton.addActionListener(e -> {
            JFrame tutorialFrame = new JFrame("How to Play");
            tutorialFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            tutorialFrame.setPreferredSize(new Dimension(600, 250));
            tutorialFrame.setLayout(new BorderLayout());

            JTextArea tutorialText = new JTextArea();
            tutorialText.setEditable(false);
            tutorialText.setFont(new Font("Serif", Font.PLAIN, 17));
            tutorialText.setText(
                    "How to Play:\n" +
                            "1. Use 'A' and 'D' keys to move the paddle left and right.\n" +
                            "2. The objective is to break all the bricks by bouncing the ball off the paddle.\n" +
                            "3. If the ball hits a brick, the brick will be destroyed and the ball will bounce back.\n" +
                            "4. If the ball hits the walls, it will bounce back.\n" +
                            "5. If the ball falls below the paddle, the game will end.\n" +
                            "Good luck and have fun!"
            );

            JScrollPane scrollPane = new JScrollPane(tutorialText);
            tutorialFrame.add(scrollPane, BorderLayout.CENTER);

            tutorialFrame.pack();
            tutorialFrame.setVisible(true);
        });
    }

}
