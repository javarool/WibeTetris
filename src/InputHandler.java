import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {
    private GamePanel gamePanel;
    
    public InputHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (gamePanel.getGameState().isGameOver()) {
            // Only allow restart when game is over
            if (e.getKeyCode() == KeyEvent.VK_R) {
                gamePanel.restartGame();
            }
            return;
        }
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                gamePanel.getBoard().movePieceLeft();
                gamePanel.repaint();
                break;
                
            case KeyEvent.VK_RIGHT:
                gamePanel.getBoard().movePieceRight();
                gamePanel.repaint();
                break;
                
            case KeyEvent.VK_DOWN:
                gamePanel.getBoard().movePieceDown();
                gamePanel.repaint();
                break;
                
            case KeyEvent.VK_UP:
                gamePanel.getBoard().rotatePiece();
                gamePanel.repaint();
                break;
                
            case KeyEvent.VK_SPACE:
                int dropDistance = gamePanel.getBoard().hardDrop();
                // Add bonus points for hard drop
                if (dropDistance > 0) {
                    gamePanel.getGameState().addScore(dropDistance);
                }
                gamePanel.repaint();
                break;
                
            case KeyEvent.VK_P:
                gamePanel.pauseGame();
                break;
                
            case KeyEvent.VK_R:
                gamePanel.restartGame();
                break;
        }
    }
}
