import java.awt.Color;
import java.util.Random;

public class Tetromino {
    private int[][] shape;
    private Color color;
    private static final Random random = new Random();
    
    // Tetromino shapes (I, O, T, S, Z, J, L)
    private static final int[][][] I_SHAPE = {
        {{0, 0, 0, 0},
         {1, 1, 1, 1},
         {0, 0, 0, 0},
         {0, 0, 0, 0}},
        {{0, 0, 1, 0},
         {0, 0, 1, 0},
         {0, 0, 1, 0},
         {0, 0, 1, 0}},
        {{0, 0, 0, 0},
         {0, 0, 0, 0},
         {1, 1, 1, 1},
         {0, 0, 0, 0}},
        {{0, 1, 0, 0},
         {0, 1, 0, 0},
         {0, 1, 0, 0},
         {0, 1, 0, 0}}
    };
    
    private static final int[][][] O_SHAPE = {
        {{0, 1, 1, 0},
         {0, 1, 1, 0},
         {0, 0, 0, 0},
         {0, 0, 0, 0}}
    };
    
    private static final int[][][] T_SHAPE = {
        {{0, 1, 0, 0},
         {1, 1, 1, 0},
         {0, 0, 0, 0},
         {0, 0, 0, 0}},
        {{0, 1, 0, 0},
         {0, 1, 1, 0},
         {0, 1, 0, 0},
         {0, 0, 0, 0}},
        {{0, 0, 0, 0},
         {1, 1, 1, 0},
         {0, 1, 0, 0},
         {0, 0, 0, 0}},
        {{0, 1, 0, 0},
         {1, 1, 0, 0},
         {0, 1, 0, 0},
         {0, 0, 0, 0}}
    };
    
    private static final int[][][] S_SHAPE = {
        {{0, 1, 1, 0},
         {1, 1, 0, 0},
         {0, 0, 0, 0},
         {0, 0, 0, 0}},
        {{0, 1, 0, 0},
         {0, 1, 1, 0},
         {0, 0, 1, 0},
         {0, 0, 0, 0}},
        {{0, 0, 0, 0},
         {0, 1, 1, 0},
         {1, 1, 0, 0},
         {0, 0, 0, 0}},
        {{1, 0, 0, 0},
         {1, 1, 0, 0},
         {0, 1, 0, 0},
         {0, 0, 0, 0}}
    };
    
    private static final int[][][] Z_SHAPE = {
        {{1, 1, 0, 0},
         {0, 1, 1, 0},
         {0, 0, 0, 0},
         {0, 0, 0, 0}},
        {{0, 0, 1, 0},
         {0, 1, 1, 0},
         {0, 1, 0, 0},
         {0, 0, 0, 0}},
        {{0, 0, 0, 0},
         {1, 1, 0, 0},
         {0, 1, 1, 0},
         {0, 0, 0, 0}},
        {{0, 1, 0, 0},
         {1, 1, 0, 0},
         {1, 0, 0, 0},
         {0, 0, 0, 0}}
    };
    
    private static final int[][][] J_SHAPE = {
        {{1, 0, 0, 0},
         {1, 1, 1, 0},
         {0, 0, 0, 0},
         {0, 0, 0, 0}},
        {{0, 1, 1, 0},
         {0, 1, 0, 0},
         {0, 1, 0, 0},
         {0, 0, 0, 0}},
        {{0, 0, 0, 0},
         {1, 1, 1, 0},
         {0, 0, 1, 0},
         {0, 0, 0, 0}},
        {{0, 1, 0, 0},
         {0, 1, 0, 0},
         {1, 1, 0, 0},
         {0, 0, 0, 0}}
    };
    
    private static final int[][][] L_SHAPE = {
        {{0, 0, 1, 0},
         {1, 1, 1, 0},
         {0, 0, 0, 0},
         {0, 0, 0, 0}},
        {{0, 1, 0, 0},
         {0, 1, 0, 0},
         {0, 1, 1, 0},
         {0, 0, 0, 0}},
        {{0, 0, 0, 0},
         {1, 1, 1, 0},
         {1, 0, 0, 0},
         {0, 0, 0, 0}},
        {{1, 1, 0, 0},
         {0, 1, 0, 0},
         {0, 1, 0, 0},
         {0, 0, 0, 0}}
    };
    
    // Colors for each piece type
    private static final Color[] COLORS = {
        new Color(0, 240, 240), // I - Cyan
        new Color(240, 240, 0), // O - Yellow
        new Color(160, 0, 240), // T - Purple
        new Color(0, 240, 0),   // S - Green
        new Color(240, 0, 0),   // Z - Red
        new Color(0, 0, 240),   // J - Blue
        new Color(240, 160, 0)  // L - Orange
    };
    
    private Tetromino(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
    }
    
    public static Tetromino getRandomPiece() {
        int type = random.nextInt(7);
        int rotation = random.nextInt(4);
        
        int[][][] shapes;
        switch (type) {
            case 0: shapes = I_SHAPE; break;
            case 1: shapes = O_SHAPE; break;
            case 2: shapes = T_SHAPE; break;
            case 3: shapes = S_SHAPE; break;
            case 4: shapes = Z_SHAPE; break;
            case 5: shapes = J_SHAPE; break;
            case 6: shapes = L_SHAPE; break;
            default: shapes = I_SHAPE; break;
        }
        
        // For O piece, only one rotation
        if (type == 1) rotation = 0;
        
        return new Tetromino(shapes[rotation], COLORS[type]);
    }
    
    public Tetromino rotate() {
        int rows = shape.length;
        int cols = shape[0].length;
        int[][] rotated = new int[cols][rows];
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                rotated[col][rows - 1 - row] = shape[row][col];
            }
        }
        
        return new Tetromino(rotated, color);
    }
    
    public int[][] getShape() { return shape; }
    public Color getColor() { return color; }
    public int getWidth() { return shape[0].length; }
    public int getHeight() { return shape.length; }
}
