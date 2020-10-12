package main.view;

import javafx.scene.layout.*;
import main.handlers.ClearHandler;
import main.handlers.InsertHandler;
import main.handlers.UpdateHandler;
import main.handlers.ViewHandler;
import main.repositories.StaffRepository;

/**
 * StaffPane
 * The primary (root) pane that is displayed when the program is run. It incorporates
 * all the logic needed of managing staff records, displaying various messages, and connecting
 * to database
 *
 * @author Roshan Kharel
 */
public class StaffPane extends BorderPane {
    private final Form form;
    private final FormGrid formGrid;
    private final MessageBox messageBox;
    private final ButtonPane buttonPane;
    private final StaffRepository staffRepository;

    /**
     * constructor with staff repository as a parameter
     */
    public StaffPane(StaffRepository repository) {
        super();
        staffRepository = repository;

        // initialize staff form
        form = new Form();
        // initialize form grid
        formGrid = new FormGrid(form);

        // initialize ButtonPane
        buttonPane = new ButtonPane();

        // initialize message box
        messageBox = new MessageBox();

        // setup the layout
        setLayout();

        // listen for all events
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

    /**
     * Setup the placement of various display components within this pane
     */
    private void setLayout() {
        // message box pane is placed on top of the window
        setTop(messageBox);
        // form grid is placed in the center of the screen
        setCenter(formGrid);
        // put control buttons at the bottom-center
        setBottom(buttonPane);

        // setting a light grey-ish background
        setStyle("-fx-background-color: #e7eaf3");
    }

    /**
     * Listen for various button click events
     */
    private void listenEvents() {
        // listen for view button click by supplying new instance of ViewHandler
        buttonPane.setOnViewButtonClicked(new ViewHandler(this));
        // listen for insert button click by supplying new instance of InsertHandler
        buttonPane.setOnInsertButtonClicked(new InsertHandler(this));
        // listen for update button click by supplying new instance of UpdateHandler
        buttonPane.setOnUpdateButtonClicked(new UpdateHandler(this));
        // listen for clear button click by supplying new instance of ClearHandler
        buttonPane.setOnClearButtonClicked(new ClearHandler(this));
    }
}
