package io.saqaStudio.com.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameController {
    private final MatchThree game;
    private final GameModel model;
    private final GameScreen view;

    private float accumulator = 0;

    public GameController(MatchThree game, GameModel model, GameScreen view) {
        this.game = game;
        this.model = model;
        this.view = view;
    }

    public void update(float delta) {
        if (model.isGameOver()) {
            // Игра окончена, можно обработать завершение
            return;
        }

        accumulator += delta;
        if (accumulator >= 1f) {
            model.decreaseTime();
            accumulator = 0;
            view.updateTimeBar(model.getTimeLeft());

            if (model.isGameOver()) {
                view.handleGameOver(model.getScore());
            }
        }

        view.updateScore(model.getScore());
    }

    public void onMenuPressed() {
        game.playClick();
        game.setScreen(new MenuScreen(game));
    }

    public void onSaveRecord(String name) {
        if (!name.isEmpty()) {
            game.getRecordsFile().writeString(model.getScore() + "\n", true);
            game.getRecordsFile().writeString(name + "\n", true);
        }
        game.setScreen(new RecordsScreen(game));
    }
}
