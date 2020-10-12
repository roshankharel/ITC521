package main.handlers;

import javafx.scene.input.MouseEvent;
import main.Validator;
import main.entities.Staff;
import main.view.Form;
import main.view.MessageBox;
import main.view.StaffPane;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * InsertHandler
 * This class used for listen for insert event i.e. when clicked on insert button.
 * This handler has all the logic for in place to validate the users input, displaying
 * messages when validation fails and data is inserted to database.
 *
 * @author Roshan Kharel
 */
public class InsertHandler extends Handler {
    public InsertHandler(StaffPane context) {
        super(context);
    }

    /**
     * Method is invoked by JavaFx on click dispatcher.
     */
    @Override
    public void handle(MouseEvent event) {
        // set the form mode to insert, so the id field cannot be edited
        getForm().setMode(Form.Mode.INSERT);

        // get staff from the form
        Staff staff = getStaff();

        // check staff object is not null
        if(staff == null) return;

        try {
            // create new record of the staff
            staff = getRepository().create(staff);

            // fill the form by using the newly created staff
            getForm().fill(staff);

            // display staff created success message
            getMessageBox().success(MessageBox.MSG_STAFF_RECORD_CREATED);
        } catch (SQLException throwable) {
            // there was an sql error
            throwable.printStackTrace();
        }
    }

    /**
     * Method verifies if the all fields in the form are valid
     */
    protected boolean validateForm() {
        // validate form
        ArrayList<String> errors = Validator.validateForm(getForm());

        // check if there is no error
        if (errors.size() == 0) {
            return true;
        }

        // display error messages
        getMessageBox().error(
                String.join("\n", errors)
        );

        return false;
    }

    /**
     * Method validates form fields data and gets staff object representation from the form if valid
     */
    protected Staff getStaff() {
        // check form fields' data validity
        if (!validateForm()) {
            return null;
        }

        // get and return staff from the form
        return getForm().getStaff();
    }
}
