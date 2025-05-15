package io.saqaStudio.com.model;

public class GameModel {
    private int score = 0;
    private int timeLeft = 60;
    private boolean gameOver = false;

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        score += points;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void decreaseTime() {
        if (timeLeft > 0) {
            timeLeft--;
        }
        if (timeLeft == 0) {
            gameOver = true;
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
