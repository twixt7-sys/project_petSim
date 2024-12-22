package dir.controllers;

import java.io.IOException;

import dir.Main;
import dir.utilities.Util;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class startingScrController {

    // main components
    @FXML
    public ImageView bg_img;

    @FXML
    public AnchorPane particle_pane;

    @FXML
    public StackPane root;

    // event 0 components
    @FXML
    public VBox container;

    @FXML
    public ImageView title_img, pstp_img;

    // event 1 components
    @FXML
    public StackPane div1;

    private static int eventVal = 0;

    private final boolean[] isAdded = new boolean[3];
    private static boolean blinkfade = true;

    public void initialize() throws IOException {
        Platform.runLater(() -> {
            triggerEvent();

            Util.addSpacebarListener(Main.scene, () -> {
                eventVal++;
                triggerEvent();
                Main.scene.setOnKeyPressed(null);
            });

            Util.addLeftRightKeyListeners(Main.scene, () -> {
                eventVal++;
                triggerEvent();
            }, () -> {
                eventVal--;
                triggerEvent();
            });
        });
    }

    private void triggerEvent() {
        switch (eventVal) {
            case 0 ->
                event0();
            case 1 ->
                event1();
        }
    }

    private void event0() {
        isAdded[0] = Util.addChildOnce(root, "fxcomponents/titles", this, isAdded[0]);

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
                Util.blinkFade(pstp_img, 1, blinkfade);
            });
        });
    }

    private void exit0(double delay_time) {
        Util.easeOut(title_img, delay_time);
        Util.easeOut(pstp_img, delay_time);
        blinkfade = false;
        Util.delay(delay_time, () -> {
            pstp_img.setOpacity(1);
            pstp_img.getTransforms().clear();
            if (container.getParent() != null) {
                Util.removeFromStackPane(container);
            }
        });
    }

    private void event1() {
        exit0(0.5);
        isAdded[1] = Util.addChildOnce(root, "interfaces/div1", this, isAdded[1]);
        Util.easeIn(div1);
    }
}
