package main.handlers;

import javafx.scene.input.MouseEvent;
import main.view.Form;
import main.view.MessageBox;
import main.view.StaffPane;

/**
 * ClearHandler
 * This class used for listen for clear event i.e. when clicked on clear button.
 * This handler resets all the form fields, any error message displayed, and makes form
 * id field editable.
 *
 * @author Roshan Kharel
 */
public class ClearHandler extends Handler {
    public ClearHandler(StaffPane context) {
        super(context);
    }

    /**
     * Method is invoked by JavaFx on click dispatcher.
     */
    @Override
    public void handle(MouseEvent event) {
        // set form mode to clear, so id text field is editable
        getForm().setMode(Form.Mode.CLEAR);
        // show default greeting message
        getMessageBox().info(MessageBox.MSG_GREET);
        // reset the form fields
        getForm().reset();
    }
}
