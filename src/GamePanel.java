import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;
/**
 * This class represents the game panel where the game takes place. It handles the game logic,
 * rendering, and user input.
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {
    public static final int WIDTH = 900;
    public static final int HEIGHT = 800;
    private Ball ball;
    private Paddle paddle;
    private List<Block> bricks;
    private Timer timer;
    private boolean inGame = true;
    private boolean isPaused = false; // New variable for pause functionality
    private MovementStrategy movementStrategy;
    private int difficultyLevel;

    /**
     * Constructs a new game panel with the specified difficulty level.
     *
     * @param difficultyLevel the difficulty level of the game
     */
    public GamePanel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addKeyListener(this);
        movementStrategy = new BPMovementStrategy();
        startGame();
    }

    /**
     * Method for starting the game by initializing game objects and setting up the timer.
     */
    private void startGame() {
        inGame = true;
        int ballSpeedX = 2;
        int ballSpeedY = 2;
        int paddleSpeed = 5;
        int numBricksPerRow = 12;

        switch (difficultyLevel) {
            case 1: // Easy
                numBricksPerRow = 8;

                break;
            case 2: // Medium
                numBricksPerRow = 11;
                ballSpeedX = 0;
                break;
            case 3: // Hard

                break;
        }


        ball = new Ball(260, 400, 20, ballSpeedX, ballSpeedY, true);
        paddle = new Paddle(300, 500, 100, 15, paddleSpeed);
        bricks = new ArrayList<>();
        int numRows = 4;
        int margin = 3;
        int brickWidth = (GamePanel.WIDTH - (margin * (numBricksPerRow + 1))) / numBricksPerRow;
        int brickHeight = 20;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numBricksPerRow; col++) {
                int x = col * (brickWidth + margin) + margin;
                int y = row * (brickHeight + margin) + 50;
                bricks.add(new Block(x, y, brickWidth, brickHeight));
            }
        }

        timer = new Timer(5, this);
        timer.start();
        requestFocus();
        removeAll();
        repaint();
    }
    /**
     * Handles the key press events to move the paddle.
     *
     * @param e the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            paddle.moveLeft();
        } else if (key == KeyEvent.VK_D) {
            paddle.moveRight();
        }
    }
    /**
     * Handles the key release events to stop the paddle.
     *
     * @param e the key event
     */

    @Override
    public void keyReleased(KeyEvent e) {
        if(difficultyLevel ==1 || difficultyLevel ==2){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_D) {
            paddle.stop();
        }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    /**
     * Renders the game objects on the panel.
     */
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
            String message = bricks.isEmpty() ? "You Win!" : "Game Over";
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
    /**
     * Handles the action events triggered by the game timer.
     *
     * @param e the action event(timer in our case)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            movementStrategy.move(ball);
            movementStrategy.moveP(paddle);

            if (CollisionDetector.checkCollision(ball, paddle)) {
                ball.reverseY();
            }

            Iterator<Block> iterator = bricks.iterator();
            while (iterator.hasNext()) {
                Block brick = iterator.next();
                if (CollisionDetector.checkCollision(ball, brick)) {
                    ball.reverseY();
                    iterator.remove();
                    if (difficultyLevel == 2) {
                        ball.increaseSpeed();
                    }
                }
            }

            if (ball.getY() + ball.getDiameter() > HEIGHT) {
                inGame = false;
                timer.stop();
            }

            if (bricks.isEmpty()) {
                inGame = false;
                timer.stop();
            }

            repaint();
        }
    }
    /**
     * Creates the game menu with options to start the game, view the tutorial, or exit.
     * After the game has started, creates "Select Level" where you have to select what level you would like to play.
     */
    public static void createGameMenu() {
        JFrame frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

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
            JFrame levelFrame = new JFrame("Select Level");
            levelFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            levelFrame.setSize(400, 300);

            JPanel levelPanel = new JPanel();
            JButton easyButton = new JButton("Easy");
            JButton mediumButton = new JButton("Medium");
            JButton hardButton = new JButton("Hard");

            levelPanel.add(easyButton);
            levelPanel.add(mediumButton);
            levelPanel.add(hardButton);

            levelFrame.add(levelPanel);
            levelFrame.setVisible(true);

            easyButton.addActionListener(ev -> startGame(1));
            mediumButton.addActionListener(ev -> startGame(2));
            hardButton.addActionListener(ev -> startGame(3));
        });

        tutorialButton.addActionListener(e -> {
            JFrame tutorialFrame = new JFrame("How to Play");
            tutorialFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            tutorialFrame.setPreferredSize(new Dimension(700, 325));
            tutorialFrame.setLayout(new BorderLayout());

            JTextArea tutorialText = new JTextArea();
            tutorialText.setEditable(false);
            tutorialText.setFont(new Font("Serif", Font.PLAIN, 17));
            tutorialText.setText(
                    "How to Play:\n" +
                            "1. Open the game menu and click the 'Start' button.\n" +
                            "2. Choose a difficulty level: Easy, Medium, or Hard.\n" +
                            "   - Easy: Classic Breakout gameplay.\n" +
                            "   - Medium: The ball bounces in a random direction after hitting a block.\n" +
                            "   - Hard: The paddle continues to move in one direction until it hits the edge of the screen.\n" +
                            "3. Use 'A' and 'D' keys to move the paddle left and right.\n" +
                            "4. The objective is to break all the bricks by bouncing the ball off the paddle.\n" +
                            "5. If the ball hits a brick, the brick will be destroyed and the ball will bounce back.\n" +
                            "6. If the ball hits the walls, it will bounce back.\n" +
                            "7. If the ball falls below the paddle, the game will end.\n" +
                            "Good luck and have fun!"
            );

            JScrollPane scrollPane = new JScrollPane(tutorialText);
            tutorialFrame.add(scrollPane, BorderLayout.CENTER);

            tutorialFrame.pack();
            tutorialFrame.setVisible(true);
        });
    }
    /**
     * Starts the game with the specified difficulty level.
     *
     * @param difficultyLevel the difficulty level to start the game with
     */
      private static void startGame(int difficultyLevel) {
        JFrame gameFrame = new JFrame("Game Field");
        GamePanel gamePanel = new GamePanel(difficultyLevel);
        gameFrame.add(gamePanel);
        gameFrame.pack();
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame.setVisible(true);
    }
}