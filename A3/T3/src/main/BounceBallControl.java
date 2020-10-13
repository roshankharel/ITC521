package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * BounceBallControl BounceBallControl is a JavaFX application which displays a animation of a
 * circle moving between the its window boundaries.
 *
 * @author Roshan Kharel
 */
public class BounceBallControl extends Application {
    private BallPane ballPane;
    private Label speedLabel;

    @Override
    public void start(Stage primaryStage) {
        ballPane = new BallPane(); // Create a ball pane

        speedLabel = new Label();
        speedLabel.setPadding(new Insets(3, 10, 3, 10));
        // styling label fx-css
        speedLabel.setStyle("-fx-background-color: rgba(69,39,160, 0.35); -fx-font-weight: bold; " +
                                    "-fx-background-radius: 0 0 10 10;");

        speedLabel.setTextFill(Color.web("#311b92"));
        speedLabel.setTextAlignment(TextAlignment.CENTER);
        // makes ball visible (on top) when overlapped with the label
        speedLabel.toBack();

        // to align label on center of the window HBox is used
        HBox labelBox = new HBox(speedLabel);
        labelBox.setAlignment(Pos.CENTER);

        // put labelBox (HBox) on top side of the screen
        ballPane.setTop(labelBox);

        // on mouse press pause the animation
        ballPane.setOnMousePressed(e -> {
            ballPane.pause();
            updateSpeedLabelText();
        });

        // on mouse press resume the animation
        ballPane.setOnMouseReleased(e -> {
            ballPane.play();
            updateSpeedLabelText();
        });

        // when ball speed is changed update the text of the label
        ballPane.setOnSpeedChanged(this::updateSpeedLabelText);

        // increase and decrease animation speed
        ballPane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                // ok key up increase the speed
                ballPane.increaseSpeed();
            } else if (e.getCode() == KeyCode.DOWN) {
                // on down decrease
                ballPane.decreaseSpeed();
            }
        });

        // create a scene and place it in the stage
        Scene scene = new Scene(ballPane, 400, 175);
        primaryStage.setTitle("Bounce Ball Control"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        // Must request focus after the primary stage is displayed
        ballPane.requestFocus();
        ballPane.setStyle("-fx-background-color: #ede7f6");

        // initially set the text fir speed label
        updateSpeedLabelText();
    }

    /**
     * This method simply updates the label with current speed of the ball
     */
    private void updateSpeedLabelText() {
        // check if ball is paused
        if (ballPane.isPaused()) {
            // ball is paused, the ball has no speed
            speedLabel.setText("0.00");
            return;
        }

        // update the label with current speed
        speedLabel.setText(String.format("%.2f", ballPane.getSpeed()));
    }
}
