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
import javafx.scene.control.TextField;
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
    @FXML
    public VBox vb1, vb2;

    // enter pet name components
    @FXML
    public VBox vb_entername;
    @FXML
    public TextField tf;

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
        events.put("enter_pet_name", new EventNode(eventHandlers::entranceEnterPetName, eventHandlers::eventEnterPetName, eventHandlers::exitEnterPetName));
        events.put("switchToGame", new EventNode(eventHandlers::entranceSwitchToGame, eventHandlers::eventSwitchToGame, eventHandlers::exitSwitchToGame));

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

    @FXML
    private void dogButtonClicked() {
        System.out.println("dog");
        Main.pet = "dog";
        setCurrentEvent("enter_pet_name");
    }

    @FXML
    private void catButtonClicked() {
        System.out.println("cat");
        Main.pet = "cat";
        setCurrentEvent("enter_pet_name");
    }

    @FXML
    private void frogButtonClicked() {
        System.out.println("frog");
        Main.pet = "frog";
        setCurrentEvent("enter_pet_name");
    }

    @FXML
    private void handleEnterKey() {
        System.out.println("Enter key pressed");
        System.out.println("Pet name: " + tf.getText());
        setCurrentEvent("switchToGame");
    }
}
