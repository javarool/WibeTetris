import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 20;
    private static final int CELL_SIZE = 40;
    private static final int PANEL_WIDTH = BOARD_WIDTH * CELL_SIZE;
    private static final int PANEL_HEIGHT = BOARD_HEIGHT * CELL_SIZE;
    
    private Board board;
    private GameState gameState;
    private InputHandler inputHandler;
    private Timer gameTimer;
    private boolean isGameRunning;
    
    public GamePanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        
        // Initialize game components
        board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        gameState = new GameState();
        inputHandler = new InputHandler(this);
        
        // Add key listener
        addKeyListener(inputHandler);
        
        // Set up game timer
        gameTimer = new Timer(1000, this); // 1 second initial interval
    }
    
    public void startGame() {
        isGameRunning = true;
        gameTimer.start();
        requestFocusInWindow();
    }
    
    public void pauseGame() {
        if (isGameRunning) {
            isGameRunning = false;
            gameTimer.stop();
        } else {
            isGameRunning = true;
            gameTimer.start();
        }
        repaint();
    }
    
    public void restartGame() {
        board.clear();
        gameState.reset();
        gameTimer.setDelay(1000);
        startGame();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw board
        board.draw(g2d, CELL_SIZE);
        
        // Draw game state info
        drawGameInfo(g2d);
        
        // Draw game over screen if needed
        if (gameState.isGameOver()) {
            drawGameOver(g2d);
        }
    }
    
    private void drawGameInfo(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        
        String scoreText = "Score: " + gameState.getScore();
        String levelText = "Level: " + gameState.getLevel();
        String linesText = "Lines: " + gameState.getLinesCleared();
        
        g2d.drawString(scoreText, 10, 20);
        g2d.drawString(levelText, 10, 40);
        g2d.drawString(linesText, 10, 60);
        
        if (!isGameRunning && !gameState.isGameOver()) {
            g2d.setColor(Color.YELLOW);
            g2d.drawString("PAUSED", PANEL_WIDTH / 2 - 30, PANEL_HEIGHT / 2);
        }
    }
    
    private void drawGameOver(Graphics2D g2d) {
        // Semi-transparent overlay
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        
        // Game over text
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        String gameOverText = "GAME OVER";
        FontMetrics fm = g2d.getFontMetrics();
        int x = (PANEL_WIDTH - fm.stringWidth(gameOverText)) / 2;
        int y = PANEL_HEIGHT / 2 - 20;
        g2d.drawString(gameOverText, x, y);
        
        // Instructions
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        String restartText = "Press R to restart";
        fm = g2d.getFontMetrics();
        x = (PANEL_WIDTH - fm.stringWidth(restartText)) / 2;
        y = PANEL_HEIGHT / 2 + 20;
        g2d.drawString(restartText, x, y);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameRunning && !gameState.isGameOver()) {
            // Move current piece down
            if (!board.movePieceDown()) {
                // Piece landed, place it and check for lines
                board.placePiece();
                int linesCleared = board.clearLines();
                if (linesCleared > 0) {
                    gameState.addScore(linesCleared);
                    gameState.addLines(linesCleared);
                    
                    // Increase speed every 10 lines
                    if (gameState.getLinesCleared() % 10 == 0) {
                        int newDelay = Math.max(100, 1000 - (gameState.getLevel() * 100));
                        gameTimer.setDelay(newDelay);
                    }
                }
                
                // Spawn new piece
                if (!board.spawnNewPiece()) {
                    gameState.setGameOver(true);
                }
            }
            repaint();
        }
    }
    
    // Getters for input handler
    public Board getBoard() { return board; }
    public GameState getGameState() { return gameState; }
    public boolean isGameRunning() { return isGameRunning; }
}
