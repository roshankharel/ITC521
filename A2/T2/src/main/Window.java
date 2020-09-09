package main;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.application.Application;

import java.util.ArrayList;

public class Window extends Application {
    // dimension of the window
    static final double HEIGHT = 400;
    static final double WIDTH = 325;
    static final String TITLE = "Show Points";

    protected Repository repository;
    protected Storage storage;
    protected EventBus eventBus;
    protected Text textCoordinate;

    public Window() {
        eventBus = EventBus.getInstance();
        storage = Storage.getInstance();
        repository = Repository.getInstance();

        bootstrap();
    }

    public void bootstrap() {
        repository.addPoints(storage.loadPoints());
    }

    private void registerEventListeners(Stage stage) {
        stage.setOnCloseRequest(event -> System.out.println("Application Closed!"));

        eventBus.on(EventBus.EventType.DRAG_COMPLETE, (event, point) -> storage.savePoints(repository.getPoints()));

        eventBus.on(EventBus.EventType.MOUSE_ENTER, (event, point) -> {
            textCoordinate.setText(String.format("X=%.2f, Y=%.2f", point.getX(), point.getY()));
            textCoordinate.setVisible(true);
        });

        eventBus.on(EventBus.EventType.DRAGGING, (event, point) -> {
            textCoordinate.setText(String.format("X=%.2f, Y=%.2f", point.getX(), point.getY()));
            textCoordinate.setVisible(true);
        });

        eventBus.on(EventBus.EventType.MOUSE_EXIT, (event, point) -> {
            textCoordinate.setText(null);
            textCoordinate.setVisible(false);
        });
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(getPane(), WIDTH, HEIGHT);
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true); // turns off window resize
        primaryStage.show();

        registerEventListeners(primaryStage);
    }

    /**
     * Returns Pane object with drawn triangles as children for the scene
     */
    public Pane getPane() {
        BorderPane pane = new BorderPane();
        ObservableList<Node> children = pane.getChildren();

        ArrayList<Point> points = repository.getPoints();

        for (Point p : points)
            children.add(p.getCircle());

        textCoordinate = new Text();
        textCoordinate.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        textCoordinate.setVisible(false);
        textCoordinate.setFill(Color.DARKBLUE);

        StackPane stackPane = new StackPane(textCoordinate);
        stackPane.setPadding(new Insets(10));

        pane.setBottom(stackPane);

        // set light grey background color to the pane
        pane.setStyle("-fx-background-color: #ECEFF1;");

        return pane;
    }
}
