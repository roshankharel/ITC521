package main.handlers;

import javafx.scene.input.MouseEvent;
import main.entities.Staff;
import main.view.Form;
import main.view.MessageBox;
import main.view.StaffPane;

import java.sql.SQLException;

/**
 * UpdateHandler
 * This class used for listen for update event i.e. when clicked on update button.
 * This handler has all the logic for in place to validate the users input, displaying
 * messages when validation fails and data is updated to database. Form validation failure
 * and error message displaying logic is utilized by extending InsertHandler class
 *
 * @author Roshan Kharel
 */
public class UpdateHandler extends InsertHandler {
    public UpdateHandler(StaffPane context) {
        super(context);
    }

    /**
     * Method is invoked by JavaFx on click dispatcher.
     */
    @Override
    public void handle(MouseEvent event) {
        // set the form mode to update, so the id field cannot be edited
        getForm().setMode(Form.Mode.UPDATE);

        // get staff id from the form field
        String idStr = getForm().getIdTextField().getText();

        // get staff object representation from the form
        Staff staff = getStaff();

        // verify staff object
        if(staff == null) return;

        // set the id of staff object
        staff.setId(Integer.parseInt(idStr));

        try {
            // update the staff object
            staff = getRepository().update(staff);
            // update the form fields to reflect the update
            getForm().fill(staff);

            // show success message of update
            getMessageBox().success(MessageBox.MSG_STAFF_RECORD_UPDATED);
        } catch (SQLException throwable) {
            // there was an sql error
            throwable.printStackTrace();
        }
    }
}
