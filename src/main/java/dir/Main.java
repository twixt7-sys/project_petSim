package dir;

import java.io.IOException;

import dir.utilities.Util;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {

    public static ImageView bg_img = Util.createImage("others/bg");
    public static MediaPlayer mp = Util.createMediaPlayer("bgm");
    public static Scene scene = Util.createScene("startingScreen", 600, 900);

    @Override
    public void start(Stage stage) throws IOException {
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
