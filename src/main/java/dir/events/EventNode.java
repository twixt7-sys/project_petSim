package dir.events;

public class EventNode {

    public Runnable entrance;
    public Runnable body;
    public Runnable exit;

    public EventNode(Runnable entrance, Runnable body, Runnable exit) {
        this.entrance = entrance;
        this.body = body;
        this.exit = exit;
    }
}
