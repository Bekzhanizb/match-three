package io.saqaStudio.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/**
 * Simplified Facade for audio and record file operations.
 */
public class GameServices {
    // Sounds
    private static final Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("sound/touch_glass.ogg"));
    private static final Sound swapSuccessSound = Gdx.audio.newSound(Gdx.files.internal("sound/swap_success.ogg"));

    // Records file
    private static final FileHandle recordsFile = Gdx.files.local("Records");

    private GameServices() {
        // no instantiation
    }

    // Audio methods
    public static void playClick() {
        clickSound.play(0.3f);
    }

    public static void playSwapSuccess() {
        swapSuccessSound.play(0.2f);
    }

    // Record file methods
    public static void saveRecords(String content) {
        recordsFile.writeString(content, false);
    }

    public static String loadRecords() {
        return recordsFile.exists() ? recordsFile.readString() : "";
    }

    public static boolean recordsExist() {
        return recordsFile.exists();
    }

    // Dispose resources when exiting
    public static void dispose() {
        clickSound.dispose();
        swapSuccessSound.dispose();
    }
}
