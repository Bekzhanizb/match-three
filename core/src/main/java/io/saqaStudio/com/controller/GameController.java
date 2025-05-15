package io.saqaStudio.com.controller;

import io.saqaStudio.com.model.Field;
import io.saqaStudio.com.model.Tile;

public class GameController {

    public enum GameState {
        READY,
        RUNNING,
        PAUSED,
        GAME_OVER
    }

    private Field field;
    private int currentPlayer; // 1 или 2 (или другой способ обозначения)
    private GameState gameState;
    private int player1Score;
    private int player2Score;

    public GameController(Field field) {
        this.field = field;
        this.currentPlayer = 1;
        this.gameState = GameState.READY;
        this.player1Score = 0;
        this.player2Score = 0;
    }

    // Начать игру
    public void startGame() {
        gameState = GameState.RUNNING;
        currentPlayer = 1;
        player1Score = 0;
        player2Score = 0;
        field.reset(); // метод очистки/перезапуска игрового поля
    }

    // Обработка хода игрока, например, по нажатию на ячейку поля
    public boolean makeMove(int x, int y) {
        if (gameState != GameState.RUNNING) {
            System.out.println("Игра не запущена");
            return false;
        }

        Tile tile = field.getTile(x, y);
        if (tile == null || !tile.isMovable()) {
            System.out.println("Невозможный ход");
            return false;
        }

        // Логика применения хода (зависит от игры)
        boolean moveSuccess = field.processMove(currentPlayer, x, y);
        if (!moveSuccess) {
            System.out.println("Ход не удался");
            return false;
        }

        // Обновляем счёт игрока
        int pointsGained = field.calculatePoints(currentPlayer);
        if (currentPlayer == 1) {
            player1Score += pointsGained;
        } else {
            player2Score += pointsGained;
        }

        // Проверяем состояние игры — например, закончилась ли игра
        if (field.isGameOver()) {
            gameState = GameState.GAME_OVER;
            announceWinner();
        } else {
            switchPlayer();
        }

        return true;
    }

    // Смена игрока
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
        System.out.println("Ход игрока " + currentPlayer);
    }

    // Получить текущего игрока
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    // Получить состояние игры
    public GameState getGameState() {
        return gameState;
    }

    // Получить счет игрока
    public int getPlayerScore(int player) {
        if (player == 1) return player1Score;
        if (player == 2) return player2Score;
        return 0;
    }

    // Объявить победителя (можно расширить для UI или логирования)
    private void announceWinner() {
        System.out.println("Игра окончена!");
        if (player1Score > player2Score) {
            System.out.println("Победитель: Игрок 1 с " + player1Score + " очками");
        } else if (player2Score > player1Score) {
            System.out.println("Победитель: Игрок 2 с " + player2Score + " очками");
        } else {
            System.out.println("Ничья!");
        }
    }

    // Поставить игру на паузу
    public void pauseGame() {
        if (gameState == GameState.RUNNING) {
            gameState = GameState.PAUSED;
        }
    }

    // Возобновить игру
    public void resumeGame() {
        if (gameState == GameState.PAUSED) {
            gameState = GameState.RUNNING;
        }
    }
}
