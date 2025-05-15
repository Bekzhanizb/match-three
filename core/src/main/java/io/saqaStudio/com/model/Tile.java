package io.saqaStudio.com.model;

public class Tile {
    private int type;
    private boolean isMatched = false;

    public Tile(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }
}
