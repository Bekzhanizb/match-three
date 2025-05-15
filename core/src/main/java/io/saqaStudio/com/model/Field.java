package io.saqaStudio.com.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.List;

public class Field extends Group {
    private Tile[][] tiles;
    private int width;
    private int height;
    private int score = 0;

    private TextureRegion background;
    private List<TextureRegion> tileTextures;

    public Field(TextureRegion background, List<TextureRegion> tileTextures) {
        this.background = background;
        this.tileTextures = tileTextures;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        score += points;
    }


    public void dispose() {
    }
}
