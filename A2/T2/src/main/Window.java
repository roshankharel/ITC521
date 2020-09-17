package main;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * The Window program implements an javafx application that
 * displays an array of points as circle on a 2D pane. The points
 * can be manually changed by editing the points.txt available in the
 * application root. All points drawn on the screen are draggable using
 * the mouse click. The points when hovered on a circle displays the
 * its coordinates on the bottom center of the screen. And once a
 * circle is dragged the coordinate displayed at the bottom center gets
 * updated.
 *
 * @author Roshan Kharel
 * @version 1.0
 * @since 2020/09/12
 */
public class Window extends Application {
    // dimension of the window
    static final double HEIGHT = 400;
    static final double WIDTH = 325;

    // title of the window
    static final String TITLE = "Show Points";

    // central repository for the points
    protected PointRepository pointRepository;
    // points.txt file reader and Point object parser
    protected PointFileReader pointFileReader;
    // Event listener and dispatcher
    protected EventBus eventBus;
    // Text shape to hold the coordinate of hovered point
    protected Text textCoordinate;

    public Window() {
        // initialize instance variables
        eventBus = EventBus.getInstance();
        pointFileReader = PointFileReader.getInstance();
        pointRepository = PointRepository.getInstance();

        bootstrap();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Internal method to run required methods before the application
     * window is visible
     */
    private void bootstrap() {
        // load points from the file to the repository
        pointRepository.addPoints(pointFileReader.readPoints());

        // listen for events on EventBus triggered by the Point objects

        // a point triggered mouse entered event
        eventBus.on(EventBus.EventType.MOUSE_ENTERED, new EventBus.IEventListener() {
            @Override
            public void dispatch(Event event, Point point) {
                // set the coordinate of the Text shape
                textCoordinate.setText(point.toString());
                // make it visible
                textCoordinate.setVisible(true);
            }
        });

        // a point triggered dragging event
        eventBus.on(EventBus.EventType.DRAGGING, new EventBus.IEventListener() {
            @Override
            public void dispatch(Event event, Point point) {
                // set the coordinate of the Text shape
                textCoordinate.setText(point.toString());
                // make it visible
                textCoordinate.setVisible(true);
            }
        });

        // a point triggered mouse exited event
        eventBus.on(EventBus.EventType.MOUSE_EXITED, new EventBus.IEventListener() {
            @Override
            public void dispatch(Event event, Point point) {
                // make text invisible
                textCoordinate.setVisible(false);
            }
        });
    }

    @Override
    public void start(Stage primaryStage) {
        // create a scene
        Scene scene = new Scene(getPane(), WIDTH, HEIGHT);
        // set title of the main stage
        primaryStage.setTitle(TITLE);
        // set the created scene
        primaryStage.setScene(scene);
        // enable stage resize
        primaryStage.setResizable(true);
        // make stage visible
        primaryStage.show();

        // show alert if there are no points to render on screen
        if (pointRepository.getPoints().size() == 0) {
            // create a warning alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            // set a generic title
            alert.setTitle("Warning");
            // set header
            alert.setHeaderText("Points file unreadable or empty.");
            // set the main content
            alert.setContentText("Either points file does not exist or its empty");

            // wait for it to close
            alert.showAndWait();
        }
    }

    /**
     * Returns Pane object with positioned circles as children for the scene
     *
     * @return Pane
     */
    public Pane getPane() {
        // create a border pane
        BorderPane pane = new BorderPane();
        // get its observable node(children) list
        ObservableList<Node> children = pane.getChildren();

        // get all points from the point repo
        ArrayList<Point> points = pointRepository.getPoints();

        // add the underlying circle shape of each points
        // to the borderpane children list
        for (Point p : points)
            children.add(p.getCircle());

        // initialize textCoordinate
        textCoordinate = new Text();
        // set font to Arial 12pt
        textCoordinate.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        // set it invisible by default
        textCoordinate.setVisible(false);
        // set a dark text color
        textCoordinate.setFill(Color.DARKBLUE);

        // create a new stack pane and add textCoordinate as its child
        StackPane stackPane = new StackPane(textCoordinate);
        // add 10px padding to the stack pane
        stackPane.setPadding(new Insets(10));

        // on border pane, set stack pane as a bottom child
        pane.setBottom(stackPane);

        // set light background color to the border pane
        pane.setStyle("-fx-background-color: #ECEFF1;");

        return pane;
    }
}
