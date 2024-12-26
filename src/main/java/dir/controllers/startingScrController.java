package dir.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import dir.Main;
import dir.events.EventNode;
import dir.utilities.Util;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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

    // choose pet components
    @FXML
    public BorderPane choosepetdiv;
    @FXML
    public ImageView dog_img, cat_img, frog_img;

    private EventNode currentEvent = null;
    private final Stack<EventNode> eventHistory = new Stack<>();
    private final Map<String, EventNode> events = new HashMap<>();
    private final startingEvents eventHandlers;
    private final double delay = 0.5;

    // flags
    private boolean isEventSet = false;

    public startingScrController() {
        this.eventHandlers = new startingEvents(this);
    }

    public void initialize() throws IOException {
        events.put("event0", new EventNode(eventHandlers::entrance0, eventHandlers::event0, eventHandlers::exit0));
        events.put("event1", new EventNode(eventHandlers::entrance1, eventHandlers::event1, eventHandlers::exit1));
        events.put("choose_pet", new EventNode(eventHandlers::entranceChoosePet, eventHandlers::eventChoosePet, eventHandlers::exitChoosePet));

        Platform.runLater(() -> {
            if (!isEventSet) {
                setCurrentEvent("event0");
                isEventSet = true;
            }
            Util.addLeftRightKeyListeners(Main.scene, this::nextEvent, this::previousEvent);
        });
    }

    public void setCurrentEvent(String eventName) {
        EventNode newEvent = events.get(eventName);
        if (currentEvent != null) {
            currentEvent.exit.run();
            eventHistory.push(currentEvent);
        }
        currentEvent = newEvent;
        Util.delay(delay, () -> {
            currentEvent.entrance.run();
            currentEvent.body.run();
        });
    }

    public void nextEvent() {
        setCurrentEvent("event1");
    }

    public void previousEvent() {
        if (!eventHistory.isEmpty()) {
            currentEvent.exit.run();
            currentEvent = eventHistory.pop();
            currentEvent.entrance.run();
            currentEvent.body.run();
        } else {
            System.out.println("No more events in history");
        }
    }

    @FXML
    private void switchToCreatePet() {
        setCurrentEvent("choose_pet");
    }

    @FXML
    private void switchToChooseLoad() {
        System.out.println("load");
    }
}
