package main.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * MessageBox
 * MessageBox is responsible for displaying different types of messages to the user. It wraps the
 * underlying mechanism to display success, error, and info messages with visually appealing
 * color presets. It provides easy API to create success, error, and info messages
 *
 * @author Roshan Kharel
 */
public class MessageBox extends HBox {
    // available message types
    public enum MessageType {
        SUCCESS,
        INFO,
        ERROR
    }

    // various constants for predefined messages
    public static final String MSG_GREET = "Enter staff id and click on view button to " +
                                                       "display staff details.";
    public static final String MSG_STAFF_RECORD_FOUND = "Staff record is found.";
    public static final String MSG_STAFF_RECORD_CREATED = "Staff record is created.";
    public static final String MSG_STAFF_RECORD_UPDATED = "Staff record is updated.";

    // background color constants for different types of messages
    private final Background INFO_BG = new Background(new BackgroundFill(Color.web("#377dff"),
            CornerRadii.EMPTY, Insets.EMPTY));
    private final Background SUCCESS_BG = new Background(new BackgroundFill(Color.web("#00c9a7"),
            CornerRadii.EMPTY, Insets.EMPTY));
    private final Background ERROR_BG = new Background(new BackgroundFill(Color.web("#ed4c78"),
            CornerRadii.EMPTY, Insets.EMPTY));

    // Text to hold any message
    private final Text text = new Text();

    /**
     * default constructor
     */
    public MessageBox() {
        // centrally align text
        text.setTextAlignment(TextAlignment.LEFT);
        // set text color to white
        text.setFill(Color.WHITE);
        // set font style
        text.setStyle("-fx-font: 14 arial;");
        // set line spacing (vertical height of each line)
        text.setLineSpacing(4);

        // on initialization show a greeting message of type INFO
        info(MSG_GREET);

        // centrally align this pane
        setAlignment(Pos.CENTER);
        // set padding for this pane, (top 8px, right 16px, bottom 8px, left 16px)
        setPadding(new Insets(8, 16, 8, 16));

        // add the text node as its children
        getChildren().add(text);
    }

    /**
     * Displays various types of messages based on the type supplied
     *
     * @param message the message to show
     * @param type the type of message
     */
    public void show(String message, MessageType type) {
        // set message
        text.setText(message);

        // depending on the message type supplied, apply the background color to this pane
        switch (type) {
            case SUCCESS:
                setBackground(SUCCESS_BG);
                break;
            case ERROR:
                setBackground(ERROR_BG);
                break;
            case INFO:
            default:
                setBackground(INFO_BG);
        }
    }

    /**
     * Displays messages of INFO type
     *
     * @param message the message to show
     */
    public void info(String message) {
        show(message, MessageType.INFO);
    }

    /**
     * Displays messages of SUCCESS type
     *
     * @param message the message to show
     */
    public void success(String message) {
        show(message, MessageType.SUCCESS);
    }

    /**
     * Displays messages of ERROR type
     *
     * @param message the message to show
     */
    public void error(String message) {
        show(message, MessageType.ERROR);
    }
}
