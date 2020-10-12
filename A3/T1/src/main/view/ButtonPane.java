package main.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * ButtonPane
 *
 * This pane creates 4 different buttons for the View, Insert, Update, and Clear.
 * The created buttons are horizontally centrally placed with some spacing between them.
 * This class provides methods to attach click event listener on all the buttons.
 *
 * @author Roshan Kharel
 */
public class ButtonPane extends HBox {
    private final Button viewButton;
    private final Button insertButton;
    private final Button updateButton;
    private final Button clearButton;

    /**
     * default constructor
     */
    public ButtonPane() {
        // initialize all buttons
        viewButton = new Button("View");
        insertButton = new Button("Insert");
        updateButton = new Button("Update");
        clearButton = new Button("Clear");

        // add 10px padding to the stack pane
        setPadding(new Insets(16, 10, 24, 10));
        // set 16px of spacing between child nodes
        setSpacing(16);
        // centrally align children
        setAlignment(Pos.CENTER);

        // add all four buttons as its children nodes
        getChildren().addAll(viewButton, insertButton, updateButton, clearButton);
    }

    /**
     * Method to allow attaching click event listener for view button
     *
     * @param handler Event listener
     */
    public void setOnViewButtonClicked(EventHandler<MouseEvent> handler) {
        viewButton.setOnMouseClicked(handler);
    }

    /**
     * Method to allow attaching click event listener for insert button
     *
     * @param handler Event listener
     */
    public void setOnInsertButtonClicked(EventHandler<MouseEvent> handler) {
        insertButton.setOnMouseClicked(handler);
    }

    /**
     * Method to allow attaching click event listener for update button
     *
     * @param handler Event listener
     */
    public void setOnUpdateButtonClicked(EventHandler<MouseEvent> handler) {
        updateButton.setOnMouseClicked(handler);
    }

    /**
     * Method to allow attaching click event listener for clear button
     *
     * @param handler Event listener
     */
    public void setOnClearButtonClicked(EventHandler<MouseEvent> handler) {
        clearButton.setOnMouseClicked(handler);
    }
}
