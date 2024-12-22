package dir.controllers;

import dir.utilities.Util;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class startingScrController {

    @FXML
    public ImageView bg_img, title_img, pstp_img;

    @FXML
    public AnchorPane particle_pane;

    @FXML
    public StackPane root;

    @FXML
    public VBox vbox;

    public void initialize() {
        Platform.runLater(() -> {
            Util.addChild(root, "fxcomponents/titles");

            Util.set_modified_bg(bg_img, particle_pane, new Color(0.7, 1, 0.5, 0.7));

            Util.bindImageHeight(bg_img, 1, 0);
            Util.bindImageHeight(title_img, 0.25, 0);
            Util.bindImageHeight(pstp_img, 0.25, 0);

            Util.bindAnchorPane(particle_pane, 1, 0);
        });
    }
}
