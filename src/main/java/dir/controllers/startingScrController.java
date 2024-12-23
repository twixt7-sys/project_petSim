package dir.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dir.Main;
import dir.events.EventNode;
import dir.utilities.Util;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
    @FXML
    public VBox comps1;

    protected static int eventVal = 0;
    protected static boolean isTransitioning = false;

    protected static final boolean[] isAdded = new boolean[3];
    protected static boolean blinkfade = true;

    protected final List<EventNode> nodes = new ArrayList<>();
    protected final startingEvents events;

    public startingScrController() {
        this.events = new startingEvents(this);
    }

    public void initialize() throws IOException {
        nodes.add(new EventNode(events::entrance0, events::event0, events::exit0));
        nodes.add(new EventNode(events::entrance1, events::event1, events::exit1));

        Platform.runLater(() -> {
            triggerEvent();
            Util.addLeftRightKeyListeners(Main.scene, this::incrementEvent, this::decrementEvent);
        });
    }

    private void triggerEvent() {
        eventVal = Math.max(0, Math.min(eventVal, nodes.size() - 1));
        System.out.println("Triggering event: " + eventVal);

        isTransitioning = true;
        int previousEvent = eventVal - 1;
        int nextEvent = eventVal;

        Util.transitionToEvent(nodes, previousEvent, nextEvent, 0.5, () -> {
            System.out.println("Transitioned to event: " + eventVal);
            isTransitioning = false;
        });
    }

    private void incrementEvent() {
        if (!isTransitioning) {
            eventVal++;
            triggerEvent();
        }
    }

    private void decrementEvent() {
        if (!isTransitioning) {
            eventVal--;
            triggerEvent();
        }
    }

    @FXML
    private void switchToCreatePet() {
        System.out.println("Button Activated");
    }

    @FXML
    private void switchToChooseLoad() {
        System.out.println("Button Activated");
    }
}
