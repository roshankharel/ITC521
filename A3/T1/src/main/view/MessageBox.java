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

public class MessageBox extends HBox {
    public enum MessageType {
        SUCCESS,
        INFO,
        ERROR
    }

    public static final String MSG_GREET = "Enter staff id and click on view button to " +
                                                       "display staff details.";
    public static final String MSG_STAFF_RECORD_FOUND = "Staff record is found.";
    public static final String MSG_STAFF_RECORD_CREATED = "Staff record is created.";
    public static final String MSG_STAFF_RECORD_UPDATED = "Staff record is updated.";

    private final Background INFO_BG = new Background(new BackgroundFill(Color.web("#377dff"),
            CornerRadii.EMPTY, Insets.EMPTY));
    private final Background SUCCESS_BG = new Background(new BackgroundFill(Color.web("#00c9a7"),
            CornerRadii.EMPTY, Insets.EMPTY));
    private final Background ERROR_BG = new Background(new BackgroundFill(Color.web("#ed4c78"),
            CornerRadii.EMPTY, Insets.EMPTY));

    private final Text text = new Text();

    public MessageBox() {
        text.setTextAlignment(TextAlignment.LEFT);
        text.setFill(Color.WHITE);
        text.setStyle("-fx-font: 14 arial;");
        text.setLineSpacing(4);

        info(MSG_GREET);

        setAlignment(Pos.CENTER);
        setPadding(new Insets(8, 16, 8, 16));
        setBackground(INFO_BG);

        getChildren().add(text);
    }

    public void show(String message, MessageType type) {
        text.setText(message);

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

    public void info(String message) {
        show(message, MessageType.INFO);
    }

    public void success(String message) {
        show(message, MessageType.SUCCESS);
    }

    public void error(String message) {
        show(message, MessageType.ERROR);
    }
}
