import java.awt.*;

public class Board {
    private int width;
    private int height;
    private int[][] grid;
    private Tetromino currentPiece;
    private int currentX, currentY;
    
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new int[height][width];
        spawnNewPiece();
    }
    
    public boolean spawnNewPiece() {
        currentPiece = Tetromino.getRandomPiece();
        currentX = width / 2 - currentPiece.getWidth() / 2;
        currentY = 0;
        
        // Check if the new piece can be placed
        return isValidPosition(currentX, currentY, currentPiece);
    }
    
    public boolean movePieceDown() {
        if (isValidPosition(currentX, currentY + 1, currentPiece)) {
            currentY++;
            return true;
        }
        return false;
    }
    
    public boolean movePieceLeft() {
        if (isValidPosition(currentX - 1, currentY, currentPiece)) {
            currentX--;
            return true;
        }
        return false;
    }
    
    public boolean movePieceRight() {
        if (isValidPosition(currentX + 1, currentY, currentPiece)) {
            currentX++;
            return true;
        }
        return false;
    }
    
    public boolean rotatePiece() {
        Tetromino rotated = currentPiece.rotate();
        if (isValidPosition(currentX, currentY, rotated)) {
            currentPiece = rotated;
            return true;
        }
        return false;
    }
    
    public int hardDrop() {
        int dropDistance = 0;
        while (isValidPosition(currentX, currentY + 1, currentPiece)) {
            currentY++;
            dropDistance++;
        }
        return dropDistance;
    }
    
    public void placePiece() {
        int[][] shape = currentPiece.getShape();
        Color color = currentPiece.getColor();
        
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] != 0) {
                    int gridRow = currentY + row;
                    int gridCol = currentX + col;
                    if (gridRow >= 0 && gridRow < height && gridCol >= 0 && gridCol < width) {
                        grid[gridRow][gridCol] = color.getRGB();
                    }
                }
            }
        }
    }
    
    public int clearLines() {
        int linesCleared = 0;
        
        for (int row = height - 1; row >= 0; row--) {
            boolean isLineFull = true;
            for (int col = 0; col < width; col++) {
                if (grid[row][col] == 0) {
                    isLineFull = false;
                    break;
                }
            }
            
            if (isLineFull) {
                // Remove the line and shift down
                for (int r = row; r > 0; r--) {
                    System.arraycopy(grid[r - 1], 0, grid[r], 0, width);
                }
                // Clear the top line
                for (int col = 0; col < width; col++) {
                    grid[0][col] = 0;
                }
                linesCleared++;
                row++; // Check the same row again
            }
        }
        
        return linesCleared;
    }
    
    private boolean isValidPosition(int x, int y, Tetromino piece) {
        int[][] shape = piece.getShape();
        
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] != 0) {
                    int gridRow = y + row;
                    int gridCol = x + col;
                    
                    // Check boundaries
                    if (gridRow >= height || gridCol < 0 || gridCol >= width) {
                        return false;
                    }
                    
                    // Check collision with placed pieces
                    if (gridRow >= 0 && grid[gridRow][gridCol] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public void draw(Graphics2D g2d, int cellSize) {
        // Draw grid background
        g2d.setColor(new Color(50, 50, 50));
        g2d.fillRect(0, 0, width * cellSize, height * cellSize);
        
        // Draw grid lines
        g2d.setColor(new Color(100, 100, 100));
        for (int x = 0; x <= width; x++) {
            g2d.drawLine(x * cellSize, 0, x * cellSize, height * cellSize);
        }
        for (int y = 0; y <= height; y++) {
            g2d.drawLine(0, y * cellSize, width * cellSize, y * cellSize);
        }
        
        // Draw placed pieces
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (grid[row][col] != 0) {
                    Color color = new Color(grid[row][col]);
                    g2d.setColor(color);
                    g2d.fillRect(col * cellSize + 1, row * cellSize + 1, cellSize - 2, cellSize - 2);
                }
            }
        }
        
        // Draw current piece
        if (currentPiece != null) {
            int[][] shape = currentPiece.getShape();
            Color color = currentPiece.getColor();
            
            for (int row = 0; row < shape.length; row++) {
                for (int col = 0; col < shape[row].length; col++) {
                    if (shape[row][col] != 0) {
                        int x = (currentX + col) * cellSize;
                        int y = (currentY + row) * cellSize;
                        
                        // Draw piece with border
                        g2d.setColor(color);
                        g2d.fillRect(x + 1, y + 1, cellSize - 2, cellSize - 2);
                        
                        // Draw border
                        g2d.setColor(color.brighter());
                        g2d.drawRect(x + 1, y + 1, cellSize - 2, cellSize - 2);
                    }
                }
            }
        }
    }
    
    public void clear() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = 0;
            }
        }
        spawnNewPiece();
    }
    
    public Tetromino getCurrentPiece() { return currentPiece; }
    public int getCurrentX() { return currentX; }
    public int getCurrentY() { return currentY; }
}
