package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class BounceBallControl extends Application {
    @Override
    public void start(Stage primaryStage) {
        BallPane ballPane = new BallPane(); // Create a ball pane
        Label label = new Label(String.format("%.2f", ballPane.rateProperty().getValue()));
        label.setPadding(new Insets(4, 8, 4, 8));
        label.setStyle("-fx-background-color: blue");
        label.setTextFill(Color.WHITE);
        label.setTextAlignment(TextAlignment.CENTER);
        ballPane.getChildren().add(label);

        ballPane.rateProperty().addListener(
                (observableValue, oldRate, newRate) ->
                        label.setText(String.format("%.2f", newRate))
        );

        // Pause and resume animation
        ballPane.setOnMousePressed(e -> ballPane.pause());
        ballPane.setOnMouseReleased(e -> ballPane.play());

        // Increase and decrease animation
        ballPane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                ballPane.increaseSpeed();
            } else if (e.getCode() == KeyCode.DOWN) {
                ballPane.decreaseSpeed();
            }
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(ballPane, 250, 150);
        primaryStage.setTitle("Bounce Ball Control"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        // Must request focus after the primary stage is displayed
        ballPane.requestFocus();
        ballPane.setStyle("-fx-background-color: white");
    }
}
