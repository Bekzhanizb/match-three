package io.saqaStudio.com.model;

public class GameModel {
    private int score = 0;
    private int timeLeft = 60;
    private boolean gameOver = false;

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public int getTimeLeft() { return timeLeft; }
    public void decreaseTime() {
        if (timeLeft > 0) timeLeft--;
        if (timeLeft == 0) gameOver = true;
    }
    public void addScore(int points) { this.score += points; }
    public boolean isGameOver() { return gameOver; }
}
