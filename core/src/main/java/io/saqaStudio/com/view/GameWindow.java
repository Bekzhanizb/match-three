package io.saqaStudio.com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameWindow extends Window {

    public GameWindow(String title, Skin skin) {
        super(title, skin);

        getTitleTable().clearChildren();
        getTitleTable().defaults().space(5.0f);

        Button button = new Button(skin, "close");
        getTitleTable().add(button);

        Image image = new Image(skin, "title-bar-bg");
        image.setScaling(Scaling.stretchX);
        getTitleTable().add(image).growX();

        Label label = new Label("Match Three Game", skin);
        label.setFontScale(1.15f);
        getTitleTable().add(label).padLeft(20.0f).padRight(20.0f);

        image = new Image(skin, "title-bar-bg");
        image.setScaling(Scaling.stretchX);
        getTitleTable().add(image).growX();

        button = new Button(skin, "restore");
        getTitleTable().add(button);

        button = new Button(skin, "minimize");
        getTitleTable().add(button);
    }

    public void setMovable(Stage stage) {
        final Vector2 position = new Vector2();

        getTitleTable().addListener(new DragListener() {
            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {
                stage.stageToScreenCoordinates(position.set(x, y));
            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                Vector2 currentPos = stage.stageToScreenCoordinates(new Vector2(x, y));

                float dx = currentPos.x - position.x;
                float dy = currentPos.y - position.y;

                moveBy(dx, dy);

                position.set(currentPos);
            }
        });
    }

}
