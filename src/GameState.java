public class GameState {
    private int score;
    private int level;
    private int linesCleared;
    private boolean gameOver;
    private boolean paused;
    
    public GameState() {
        reset();
    }
    
    public void reset() {
        score = 0;
        level = 1;
        linesCleared = 0;
        gameOver = false;
        paused = false;
    }
    
    public void addScore(int linesCleared) {
        switch (linesCleared) {
            case 1:
                score += 100;
                break;
            case 2:
                score += 300;
                break;
            case 3:
                score += 500;
                break;
            case 4:
                score += 800; // Tetris!
                break;
        }
    }
    
    public void addLines(int lines) {
        linesCleared += lines;
        level = (linesCleared / 10) + 1;
    }
    
    // Getters and setters
    public int getScore() { return score; }
    public int getLevel() { return level; }
    public int getLinesCleared() { return linesCleared; }
    public boolean isGameOver() { return gameOver; }
    public boolean isPaused() { return paused; }
    
    public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }
    public void setPaused(boolean paused) { this.paused = paused; }
}
