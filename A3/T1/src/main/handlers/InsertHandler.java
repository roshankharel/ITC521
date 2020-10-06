package main.handlers;

import javafx.scene.input.MouseEvent;
import main.Validator;
import main.entities.Staff;
import main.view.Form;
import main.view.MessageBox;
import main.view.StaffPane;

import java.sql.SQLException;
import java.util.ArrayList;

public class InsertHandler extends Handler {
    public InsertHandler(StaffPane context) {
        super(context);
    }

    @Override
    public void handle(MouseEvent event) {
        getForm().setMode(Form.Mode.INSERT);
        Staff staff = getStaff();

        if(staff == null) return;

        try {
            staff = getRepository().create(staff);
            getForm().fill(staff);
            getMessageBox().success(MessageBox.MSG_STAFF_RECORD_CREATED);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    protected boolean validateForm() {
        ArrayList<String> errors = Validator.validateForm(getForm());

        if (errors.size() == 0) {
            return true;
        }

        getMessageBox().error(
                String.join("\n", errors)
        );

        return false;
    }

    protected Staff getStaff() {
        if (!validateForm()) {
            return null;
        }

        return getForm().getStaff();
    }
}
