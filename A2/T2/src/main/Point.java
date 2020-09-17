package main;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Point
 * Point class represent a Circle a 2D graph/pane
 *
 * @author Roshan Kharel
 */
public class Point {
    // a radius constant of the circle
    public static final int RADIUS = 5;
    // the underlying circle shape representing the point
    private final Circle circle;
    // the x coordinate
    protected double x;
    // the y coordinate
    protected double y;
    // mouse x and y position on the pane
    protected double mouseX, mouseY;
    // shadow effect when mouse hovers the circle
    protected DropShadow hoverShadow;

    /**
     * Constructor for the Point
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;

        // create circle shape from the specified x, y position with radius of 5px
        circle = new Circle(x, y, RADIUS);

        // set a random fill color for the circle
        circle.setFill(ColorSet.getRandomColor());
        // set cursor style to hand type when mouse hovers the circle
        circle.setCursor(Cursor.HAND);
        // set a dark stroke color
        circle.setStroke(Color.DARKBLUE);
        // set width of the stroke to 1 px
        circle.setStrokeWidth(1);

        // create drop shadow effect
        hoverShadow = new DropShadow();
        hoverShadow.setWidth(1);
        hoverShadow.setHeight(1);
        hoverShadow.setOffsetX(0);
        hoverShadow.setOffsetY(0);
        hoverShadow.setRadius(5);
        hoverShadow.setColor(Color.GRAY);

        // listen for events
        listenEvents();
    }

    /**
     * Method to move point to specified x and y coordinate
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public void moveTo(double x, double y) {
        setX(x);
        setY(y);
    }

    /**
     * Get the x coordinate
     *
     * @return x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Sets x coordinate
     *
     * @param x x coordinate
     */
    public void setX(double x) {
        this.x = x;
        circle.setCenterX(x);
    }

    /**
     * Get the y coordinate
     *
     * @return y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Sets y coordinate
     *
     * @param y y coordinate
     */
    public void setY(double y) {
        this.y = y;
        circle.setCenterY(y);
    }

    /**
     * Get the underlying Circle shape representing the Point
     *
     * @return Circle
     */
    public Circle getCircle() {
        return circle;
    }

    /**
     * Internal method, when invoked listens to MouseEnter, MouseExit,
     * MousePressed, MouseReleased, MouseDragged events on the circle
     * shape
     */
    private void listenEvents() {
        // atomic boolean state of mouse pressed
        AtomicBoolean isMousePressed = new AtomicBoolean(false);
        // atomic boolean state of mouse hover
        AtomicBoolean isHover = new AtomicBoolean(false);
        // atomic boolean state of drag initiated
        AtomicBoolean isDrag = new AtomicBoolean(false);
        // the event bus
        EventBus eventBus = EventBus.getInstance();

        // listen for hover (mouse entered) event on the circle
        circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // set hover state to true
                isHover.set(true);

                // apply drop shadow effect on the hovered circle
                circle.setEffect(hoverShadow);

                // if the circle is being dragged stop/return
                if (isDrag.get()) return;

                // dispatch the mouse enter event on the eventbus
                // and also supply the original event
                // and current Point instance
                eventBus.dispatch(EventBus.EventType.MOUSE_ENTERED, event, Point.this);
            }
        });

        // listen for hover exited (mouse exited) event on the circle
        circle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // set hover state to false
                isHover.set(false);

                // if the mouse is not pressed remove the drop shadow effect
                if (!isMousePressed.get())
                    circle.setEffect(null);

                // dispatch the mouse exited event on the eventbus
                // and also supply the original event
                // and current Point instance
                eventBus.dispatch(EventBus.EventType.MOUSE_EXITED, event, Point.this);
            }
        });

        // listen for mouse pressed event on the circle
        circle.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // set mouse pressed state to true
                isMousePressed.set(true);

                // get the current x and y coordinate of the mouse
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();

                // bring the clicked circle to the front of all siblings
                circle.toFront();
            }
        });

        // listen for mouse released event on the circle
        circle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // set mouse pressed state to false
                isMousePressed.set(false);
                // set drag state to false
                isDrag.set(false);

                // if the hover state is false remove the drop shadow effect
                if (!isHover.get())
                    circle.setEffect(null);
            }
        });

        // listen for mouse dragged event on the circle
        circle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // set mouse drag state to true
                isDrag.set(true);
                // calculate the offset x and y
                double offsetX = event.getSceneX() - mouseX;
                double offsetY = event.getSceneY() - mouseY;

                // calculate new x an y
                double newX = circle.getCenterX() + offsetX;
                double newY = circle.getCenterY() + offsetY;

                // set current mouse x and y position
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();

                // move point to the new location
                Point.this.moveTo(newX, newY);

                // dispatch the dragging event on the eventbus
                // and also supply the original event
                // and current Point instance
                eventBus.dispatch(EventBus.EventType.DRAGGING, event, Point.this);
            }
        });
    }

    /**
     * Provides string representation of the Point object
     */
    @Override
    public String toString() {
        return String.format("X=%.2f, Y=%.2f", getX(), getY());
    }
}