package main.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ButtonPane extends HBox {
    private final Button viewButton;
    private final Button insertButton;
    private final Button updateButton;
    private final Button clearButton;

    public ButtonPane() {
        viewButton = new Button("View");
        insertButton = new Button("Insert");
        updateButton = new Button("Update");
        clearButton = new Button("Clear");

        // add 10px padding to the stack pane
        setPadding(new Insets(16, 10, 24, 10));
        setSpacing(16);
        setAlignment(Pos.CENTER);

        getChildren().addAll(viewButton, insertButton, updateButton, clearButton);
    }

    public void setOnViewButtonClicked(EventHandler<MouseEvent> handler) {
        viewButton.setOnMouseClicked(handler);
    }

    public void setOnInsertButtonClicked(EventHandler<MouseEvent> handler) {
        insertButton.setOnMouseClicked(handler);
    }

    public void setOnUpdateButtonClicked(EventHandler<MouseEvent> handler) {
        updateButton.setOnMouseClicked(handler);
    }

    public void setOnClearButtonClicked(EventHandler<MouseEvent> handler) {
        clearButton.setOnMouseClicked(handler);
    }
}
