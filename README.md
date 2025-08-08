# Tetris Classic - Java Implementation

A lightweight, portable implementation of the classic Tetris game written in pure Java using Swing.

## Features

- Classic Tetris gameplay with all 7 standard tetromino pieces (I, O, T, S, Z, J, L)
- Smooth piece rotation and movement
- Line clearing with scoring system
- Level progression with increasing speed
- Pause/resume functionality
- Game over detection and restart option
- Clean, modern UI with grid lines and colored pieces

## Controls

- **Arrow Keys**: Move pieces left/right/down
- **Up Arrow**: Rotate piece clockwise
- **Spacebar**: Hard drop (instant drop)
- **P**: Pause/Resume game
- **R**: Restart game

## Scoring System

- 1 line: 100 points
- 2 lines: 300 points
- 3 lines: 500 points
- 4 lines (Tetris): 800 points
- Hard drop: 1 point per cell dropped

## Requirements

- Java 8 or higher
- No external libraries required

## Compilation and Running

### Method 1: Direct compilation
```bash
# Compile all Java files
javac *.java

# Run the game
java Main
```

### Method 2: Create JAR file
```bash
# Compile all Java files
javac *.java

# Create JAR file
jar cfm Tetris.jar manifest.txt *.class

# Run the JAR file
java -jar Tetris.jar
```

Note: For the JAR method, you'll need to create a `manifest.txt` file with:
```
Main-Class: Main
```

## Game Mechanics

- **Board Size**: 10 columns × 20 rows
- **Piece Spawning**: Pieces spawn at the top center of the board
- **Gravity**: Pieces fall at a fixed interval that increases with level
- **Collision Detection**: Prevents movement through walls or other blocks
- **Line Clearing**: Removes full rows and shifts above rows down
- **Level Progression**: Speed increases every 10 lines cleared

## File Structure

- `Main.java` - Entry point, initializes game window
- `GamePanel.java` - Main canvas, handles rendering and game loop
- `Board.java` - Manages grid state, line clearing, and collision detection
- `Tetromino.java` - Defines piece shapes, rotation, and colors
- `GameState.java` - Manages score, level, and game status
- `InputHandler.java` - Handles keyboard input and controls

## Future Enhancements

- Sound effects
- High score saving
- Next piece preview
- Hold piece functionality
- Particle effects
- Different game modes

Enjoy playing Tetris Classic!
