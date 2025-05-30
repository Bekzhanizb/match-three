package io.saqaStudio.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;

public class RecordsScreen extends ScreenAdapter {

    private final FileHandle file;
    private final MatchThree game;
    private final OrderedMap<Integer, String> recordsMap = new OrderedMap<>();

    public RecordsScreen(MatchThree game) {
        this.game = game;
        file = game.getRecordsFile();
    }

    @Override
    public void show() {
        GameWindow window = game.getWindow();
        Table mainTable = new Table();

        Label header = new Label("Records", game.getSkin());
        mainTable.add(header).align(Align.center);
        mainTable.row();

        ScrollPane scrollPane = new ScrollPane(null, game.getSkin(), "white-bg");
        scrollPane.setFadeScrollBars(false);
        scrollPane.setVariableSizeKnobs(false);

        readRecords();
        Label recordLabel = new Label("No winners yet...", game.getSkin());
        recordLabel.setAlignment(Align.center);
        if (!recordsMap.isEmpty()) {
            StringBuilder text = new StringBuilder();
            for (ObjectMap.Entry<Integer, String> record : recordsMap) {
                text.append(String.format("%-40s%-6d%n", record.value, record.key));
            }
            recordLabel.setText(text.toString());
        }
        scrollPane.setActor(recordLabel);

        mainTable.add(scrollPane).width(300).height(360).padBottom(28);
        mainTable.row();

        Table table = new Table();
        TextButton clearBtn = new TextButton("Clear", game.getSkin());
        table.defaults().space(15.0f).width(clearBtn.getWidth()*4).height(clearBtn.getHeight()*2);
        clearBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.playClick();
                if (file.exists())
                    file.delete();
                recordLabel.setText("No winners yet...");
            }
        });
        table.add(clearBtn);
        table.row();

        TextButton recordsBtn = new TextButton("Menu", game.getSkin());
        recordsBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.playClick();
                window.removeActor(mainTable);
                game.setScreen(new MenuScreen(game));
            }
        });
        table.add(recordsBtn);
        table.row();

        TextButton exitBtn = new TextButton("Exit", game.getSkin());
        exitBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.playClick();
                Gdx.app.exit();
            }
        });
        table.add(exitBtn);
        mainTable.add(table);

        window.add(mainTable);
    }

    private void readRecords() {
        String data = GameServices.loadRecords();
        if (!data.isEmpty()) {
            String[] records = data.split("\\n");
            for (int i = 0; i < records.length - 1; i += 2) {
                try {
                    int time = Integer.parseInt(records[i]);
                    String name = records[i + 1];
                    recordsMap.put(time, name);
                } catch (Exception e) {
                    // skip malformed line pairs
                }
            }
            // sort & reverse as before...
            recordsMap.orderedKeys().sort();
            recordsMap.orderedKeys().reverse();
        }
    }



}
