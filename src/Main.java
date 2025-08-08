import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create and configure the game window
        JFrame frame = new JFrame("Tetris Classic");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400, 800);
        frame.setLocationRelativeTo(null);
        
        // Create and add the game panel
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        
        // Display the window
        frame.setVisible(true);
        
        // Start the game
        gamePanel.startGame();
    }
}
