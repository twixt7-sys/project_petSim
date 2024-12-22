package dir.controllers;

import java.io.IOException;

import dir.utilities.Util;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class startingScrController {

    @FXML
    public ImageView bg_img;

    @FXML
    public ImageView title_img;

    @FXML
    public ImageView pstp_img;

    @FXML
    public AnchorPane particle_pane;

    @FXML
    public StackPane root;

    private boolean isChildAdded = false;

    public void initialize() throws IOException {
        Platform.runLater(() -> {
            if (!isChildAdded) {
                try {
                    Util.addChild(root, "fxcomponents/titles", this);
                    isChildAdded = true;
                } catch (IOException e) {
                }
            }

            //bind methods
            Util.set_modified_bg(bg_img, particle_pane, new Color(0.7, 1, 0.5, 0.7));
            Util.bindImageHeight(bg_img, 1, 0);
            Util.bindImageHeight(pstp_img, 0.04, 0);
            Util.bindImageHeight(title_img, 0.35, 0);
            Util.bindAnchorPane(particle_pane, 1, 0);

            Util.applyShadow(title_img, 0, 5, 5, 5, new Color(0, 0, 0, 0.25));

            //fade methods
            Util.fadeIn(bg_img, 6);
            Util.fadeIn(particle_pane, 30);

            Util.oscillateX(bg_img, 20, 450);

            title_img.setOpacity(0);
            pstp_img.setOpacity(0);

            Util.delay(5, () -> {
                Util.fadeIn(title_img, 3);
                Util.oscillateY(title_img, 1.5, 10);
                Util.delay(4, () -> {
                    Util.fadeIn(pstp_img, 1);
                    Util.blinkFade(pstp_img, 1);
                });
            });
        });
    }
}
