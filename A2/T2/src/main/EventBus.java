package main;

import javafx.event.Event;

import java.util.HashMap;

/**
 * EventBus
 * An Eventbus class provides a mechanism that allows different components of the system
 * to communicate with each other without knowing about each other.
 * This is specific to the Point class and predefined EventType(s)
 *
 * @author Roshan Kharel
 */
public class EventBus {
    /**
     * Types of custom events that a subsystem can listen on
     */
    public enum EventType {
        MOUSE_ENTERED,
        MOUSE_EXITED,
        DRAGGING,
    }

    /**
     * IEventListener
     * A contract all event listeners must implement
     */
    public interface IEventListener {
        /**
         * This method is invoked when the subscribed EventType is triggered
         *
         * @param event the original event
         * @param point the Point object that triggered the event
         * @see Event
         * @see Point
         */
        void dispatch(Event event, Point point);
    }

    /**
     * Holds reference to the instance object of this class
     */
    private static EventBus eventBus;
    /**
     * Holds a map of IEventListener keyed by EventType
     */
    protected HashMap<EventType, IEventListener> listeners;

    /**
     * Constructor method
     * private visibility of the constructor makes object creation
     * prohibited from external source
     */
    private EventBus() {
        listeners = new HashMap<>();
    }

    /**
     * The method to let access to same instance object of this class
     *
     * @return EventBus instance
     */
    public static EventBus getInstance() {
        // create self instance if not exist
        if (eventBus == null)
            eventBus = new EventBus();

        return eventBus;
    }

    /**
     * The method to let external source to listen to a specific event
     *
     * @param eventType type of event to listen on
     * @param listener  the event listener
     * @see EventType
     * @see IEventListener
     */
    public void on(EventType eventType, IEventListener listener) {
        // put the listener on the listeners map
        listeners.put(eventType, listener);
    }

    /**
     * The method let external source to trigger a specific event
     *
     * @param eventType type of event to listen on
     * @param event     the original event
     * @param point     the point object which triggered the event
     * @see EventType
     * @see Event
     * @see Point
     */
    public void dispatch(EventType eventType, Event event, Point point) {
        // check if listener exists for the specific eventType
        if (!listeners.containsKey(eventType)) return;

        // dispatch the event
        listeners.get(eventType).dispatch(event, point);
    }
}
