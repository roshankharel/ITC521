package main.handlers;

import javafx.scene.input.MouseEvent;
import main.Validator;
import main.entities.Staff;
import main.view.Form;
import main.view.MessageBox;
import main.view.StaffPane;

import java.sql.SQLException;

/**
 * ViewHandler
 * This class used for listen for view event i.e. when clicked on view button.
 * This handler has all the logic for in place to validate the user inputs (id only), displaying
 * messages when validation fails and data population in the form.
 *
 * @author Roshan Kharel
 */
public class ViewHandler extends Handler {
    public ViewHandler(StaffPane context) {
        super(context);
    }

    /**
     * Method is invoked by JavaFx on click dispatcher.
     */
    @Override
    public void handle(MouseEvent event) {
        // get staff id from the form field
        String idStr = getForm().getIdTextField().getText();

        // set the form mode to View, so the id field cannot be edited
        getForm().setMode(Form.Mode.VIEW);

        try {
            // reset the form
            getForm().reset();
            // only set the form id
            getForm().getIdTextField().setText(idStr);
            // validate the form
            Validator.validateForm(getForm());

            int id = Integer.parseInt(idStr.strip());

            // find staff by id
            Staff staff = getRepository().find(id);

            // fill the form as per the staff
            getForm().fill(staff);

            // display staff found success message
            getMessageBox().success(MessageBox.MSG_STAFF_RECORD_FOUND);
        } catch (RuntimeException error) {
            // form had error or staff could not be found

            // show error message
            getMessageBox().error(error.getMessage());

            // set the form mode clear, so the id field can be editable
            getForm().setMode(Form.Mode.CLEAR);
            error.printStackTrace();
        } catch (SQLException throwable) {
            // there was an sql error
            throwable.printStackTrace();
            // set the form mode clear, so the id field can be editable
            getForm().setMode(Form.Mode.CLEAR);
        }
    }
}
