package dir.controllers;

import dir.utilities.Util;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class startingScrController {

    @FXML
    public ImageView bg_img;

    public void initialize() {
        Platform.runLater(() -> {
            Util.bindImageHeight(bg_img, 1, 0);
        });
    }
}
