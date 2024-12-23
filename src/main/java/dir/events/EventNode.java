package dir.events;

import java.util.function.BiConsumer;

public class EventNode {

    public Runnable entrance;
    public Runnable event;
    public BiConsumer<Double, Runnable> exit;

    public EventNode(Runnable entrance, Runnable event, BiConsumer<Double, Runnable> exit) {
        this.entrance = entrance;
        this.event = event;
        this.exit = exit;
    }
}
