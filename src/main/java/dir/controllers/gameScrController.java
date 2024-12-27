package dir.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import dir.events.EventNode;
import dir.utilities.Util;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class gameScrController {

    @FXML
    public ImageView bg_img;

    @FXML
    public AnchorPane particle_pane;

    private EventNode currentEvent = null;
    private final Stack<EventNode> eventHistory = new Stack<>();
    private final Map<String, EventNode> events = new HashMap<>();
    private final gameEvents eventHandlers;
    private final double delay = 0.5;

    public gameScrController() {
        this.eventHandlers = new gameEvents(this);
        initializeEvents();
    }

    private void initializeEvents() {
        events.put("game_event1", new EventNode(eventHandlers::entranceGameEvent1, eventHandlers::bodyGameEvent1, eventHandlers::exitGameEvent1));

    }

    public void setCurrentEvent(String eventName) {
        EventNode newEvent = events.get(eventName);
        if (currentEvent != null) {
            currentEvent.exit.run();
            eventHistory.push(currentEvent);
        }
        currentEvent = newEvent;

        if ("game_event1".equals(eventName)) {
            currentEvent.entrance.run();
            currentEvent.body.run();
        } else {
            Util.delay(delay, () -> {
                currentEvent.entrance.run();
                currentEvent.body.run();
            });
        }
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
}
