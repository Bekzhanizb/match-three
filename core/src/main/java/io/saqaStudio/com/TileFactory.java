package io.saqaStudio.com;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;


public class TileFactory {

    public static Tile createRandomTile(Array<TextureAtlas.AtlasRegion> entities,
                                        ClickListener clickListener) {
        // pick a random index within the bounds of the entities array
        int index = MathUtils.random(0, entities.size - 1);
        // create and initialize the tile
        Tile tile = new Tile();
        tile.addListener(clickListener);
        tile.init(entities.get(index), index);
        return tile;
    }
}
