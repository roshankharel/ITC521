package main;

import java.util.HashMap;
import javafx.scene.input.MouseEvent;

public class EventBus {
    public interface IEventListener {
        public void dispatch(MouseEvent event, Point point);
    }

    public static enum EventType {
        MOUSE_ENTER,
        MOUSE_EXIT,
        DRAGGING,
        DRAG_COMPLETE,
    }

    private static EventBus eventBus;

    protected HashMap<EventType, IEventListener> listeners;

    private EventBus() {
        listeners = new HashMap<>();
    }

    public static EventBus getInstance() {
        if(eventBus == null)
            eventBus = new EventBus();

        return eventBus;
    }

    public void on(EventType eventType, IEventListener listener) {
        listeners.put(eventType, listener);
    }

    public void dispatch(EventType eventType, MouseEvent event, Point point) {
        if(!listeners.containsKey(eventType)) return;

        listeners.get(eventType).dispatch(event, point);
    }
}
