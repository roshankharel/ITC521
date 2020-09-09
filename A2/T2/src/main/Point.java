package main;

import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.concurrent.atomic.AtomicBoolean;

public class Point {
    protected double x;
    protected double y;
    protected EventBus eventBus;
    private final Circle circle;
    protected double sceneX, sceneY;
    protected DropShadow hoverShadow;

    public static final int RADIUS = 5;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        eventBus = EventBus.getInstance();
        circle = new Circle(x, y, RADIUS);

        Paint color = ColorSet.random();
        circle.setFill(color);
        circle.setCursor(Cursor.HAND);
        circle.setStroke(Color.DARKBLUE);
        circle.setStrokeWidth(1);

        hoverShadow = new DropShadow();
        hoverShadow.setWidth(1);
        hoverShadow.setHeight(1);
        hoverShadow.setOffsetX(0);
        hoverShadow.setOffsetY(0);
        hoverShadow.setRadius(5);
        hoverShadow.setColor(Color.GRAY);

        listenEvents();
    }

    public void moveTo(double x, double y) {
        setX(x);
        setY(y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setX(double x) {
        this.x = x;
        circle.setCenterX(x);
    }

    public void setY(double y) {
        this.y = y;
        circle.setCenterY(y);
    }

    private void listenEvents() {
        AtomicBoolean isMousePressed = new AtomicBoolean(false);
        AtomicBoolean isHover = new AtomicBoolean(false);
        AtomicBoolean isDrag = new AtomicBoolean(false);

        circle.setOnMouseEntered(event -> {
            isHover.set(true);

            circle.setEffect(hoverShadow);

            if(isDrag.get()) return;

            eventBus.dispatch(EventBus.EventType.MOUSE_ENTER, event, this);
        });

        circle.setOnMouseExited(event -> {
            isHover.set(false);

            if (!isMousePressed.get())
                circle.setEffect(null);

            eventBus.dispatch(EventBus.EventType.MOUSE_EXIT, event, this);
        });

        circle.setOnMousePressed(event -> {
            isMousePressed.set(true);
            sceneX = event.getSceneX();
            sceneY = event.getSceneY();

            circle.toFront();
        });

        circle.setOnMouseReleased(event -> {
            isMousePressed.set(false);

            if (isDrag.get()) {
                isDrag.set(false);
                eventBus.dispatch(EventBus.EventType.DRAG_COMPLETE, event, this);
            }

            if (!isHover.get())
                circle.setEffect(null);
        });

        circle.setOnMouseDragged(event -> {
            isDrag.set(true);
            double offsetX = event.getSceneX() - sceneX;
            double offsetY = event.getSceneY() - sceneY;

            circle.setCenterX(circle.getCenterX() + offsetX);
            circle.setCenterY(circle.getCenterY() + offsetY);

            sceneX = event.getSceneX();
            sceneY = event.getSceneY();

            moveTo(circle.getCenterX(), circle.getCenterY());

            eventBus.dispatch(EventBus.EventType.DRAGGING, event, this);
        });
    }

    @Override
    public String toString() {
        return String.format("x=%s; y=%s", getX(), getY());
    }
}