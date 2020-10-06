package main.view;

import javafx.scene.layout.*;
import main.handlers.ClearHandler;
import main.handlers.InsertHandler;
import main.handlers.UpdateHandler;
import main.handlers.ViewHandler;
import main.repositories.StaffRepository;

public class StaffPane extends BorderPane {
    private final Form form;
    private final FormGrid formGrid;
    private final MessageBox messageBox;
    private final ButtonPane buttonPane;
    private final StaffRepository staffRepository;

    public StaffPane(StaffRepository repository) {
        super();

        form = new Form();
        formGrid = new FormGrid(form);
        staffRepository = repository;
        buttonPane = new ButtonPane();
        messageBox = new MessageBox();

        setLayout();
        listenEvents();
    }

    public Form getForm() {
        return form;
    }

    public StaffRepository getStaffRepository() {
        return staffRepository;
    }

    public MessageBox getMessageBox() {
        return messageBox;
    }

    private void setLayout() {
        setTop(messageBox);
        // create a border pane
        setCenter(formGrid);
        // put control buttons at the bottom-center
        setBottom(buttonPane);

        setStyle("-fx-background-color: #e7eaf3");
    }

    private void listenEvents() {
        buttonPane.setOnViewButtonClicked(new ViewHandler(this));
        buttonPane.setOnInsertButtonClicked(new InsertHandler(this));
        buttonPane.setOnUpdateButtonClicked(new UpdateHandler(this));
        buttonPane.setOnClearButtonClicked(new ClearHandler(this));
    }
}
