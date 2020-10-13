package main;

import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * BallPane
 *
 * BallPane is the primary pane for this application and is responsible
 * for animating the ball movement. It provides methods to increase or
 * decrease the speed of the ball and pause or play/resume the ball
 * animation
 */
public class BallPane extends BorderPane {
    public final double ballRadius = 20;
    private double xPosition = ballRadius;
    private double yPosition = ballRadius;
    private double xDirection = 1;
    private double yDirection = 1;

    private final Circle circle;
    private final AtomicBoolean paused;
    private final AtomicInteger speed;

    private SpeedChangedListener speedChangedListener;

    public BallPane() {
        // vars initialization
        circle = new Circle(xPosition, yPosition, ballRadius);
        paused = new AtomicBoolean(false);
        speed = new AtomicInteger(100);

        // Set ball color
        circle.setFill(Color.web("#311b92"));

        // add the ball node into this pane
        getChildren().add(circle);

        play();
    }

    public void play() {
        paused.set(false);
        initializeAnimationThread();
    }

    public void pause() {
        paused.set(true);
    }

    public boolean isPaused() {
        return paused.get();
    }

    public void increaseSpeed() {
        speed.set(speed.get() + 5);

        speedChangedListener.inform();
    }

    public void decreaseSpeed() {
        speed.set(Math.max(5, speed.get() - 5));
        speedChangedListener.inform();
    }

    public double getSpeed() {
        return speed.get() / 100.0;
    }

    public void setOnSpeedChanged(SpeedChangedListener speedChangedListener) {
        this.speedChangedListener = speedChangedListener;
    }

    protected synchronized void moveBall() {
        // on collision with the boundary change ball direction
        if (xPosition < ballRadius || xPosition > getWidth() - ballRadius) {
            xDirection *= -1; // reverse ball movement direction in x-axis
        }

        if (yPosition < ballRadius || yPosition > getHeight() - ballRadius) {
            yDirection *= -1; // reverse ball movement direction in y-axis
        }

        // compute new position for the ball
        xPosition += xDirection * getSpeed() * 0.1;
        yPosition += yDirection * getSpeed() * 0.1;

        // apply the position
        circle.setCenterX(xPosition);
        circle.setCenterY(yPosition);
    }

    private void initializeAnimationThread() {
        // initialize animation thread
        Thread animationThread = new Thread(() -> {
            try {
                // run until the game is paused
                while (!paused.get()) {
                    // ball must be moved javafx thread for the animation to run smoothly
                    Platform.runLater(this::moveBall);
                    //sleep thread for 5 milli seconds
                    Thread.sleep(5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // makes thread terminate with the application
        // else the thread keep on running even after the application
        // is closed
        animationThread.setDaemon(true);

        // setting highest priority for the thread
        animationThread.setPriority(Thread.MAX_PRIORITY);

        // start the thread
        animationThread.start();
    }

    /**
     * SpeedChangedListener
     *
     * An interface for ball speed changed listener
     */
    public interface SpeedChangedListener {
        void inform();
    }
}
