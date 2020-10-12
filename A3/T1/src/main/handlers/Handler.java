package main.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import main.repositories.StaffRepository;
import main.view.Form;
import main.view.MessageBox;
import main.view.StaffPane;

/**
 * Handler
 * An abstract method that has built in helper methods for its subclass to consume.
 *
 * @author Roshan Kharel
 */
public abstract class Handler implements EventHandler<MouseEvent> {
    StaffPane context;

    /**
     * default constructor
     *
     * @param context StaffPane where all the needed objects are available
     */
    public Handler(StaffPane context) {
        this.context = context;
    }

    /**
     * Return form object from the staff pane
     */
    Form getForm() {
        return context.getForm();
    }

    /**
     * Return staff repository object from the staff pane
     */
    StaffRepository getRepository() {
        return context.getStaffRepository();
    }

    /**
     * Return message box object from the staff pane
     */
    MessageBox getMessageBox() {
        return context.getMessageBox();
    }
}
