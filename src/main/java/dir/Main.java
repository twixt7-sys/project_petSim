package dir;

import java.io.IOException;

import dir.utilities.Util;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static ImageView bg_img;
    public static MediaPlayer mp;
    public static Scene scene;

    public static final int[] initSize = {950, 650};

    @Override
    public void start(Stage stage) throws IOException {
        mp = Util.createMediaPlayer("bgm");
        scene = Util.createScene("roots/startingScreen", initSize[0], initSize[1]);

        setStage(stage);

        Util.playAudio(mp, true);

    }

    public static void main(String[] args) {
        launch();
    }

    private void setStage(Stage stage) {
        stage.setMinWidth(initSize[0]);
        stage.setMinHeight(initSize[1]);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
    }
}
