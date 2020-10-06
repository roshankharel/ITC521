package main.handlers;

import javafx.scene.input.MouseEvent;
import main.Validator;
import main.entities.Staff;
import main.errors.CRUDError;
import main.view.Form;
import main.view.MessageBox;
import main.view.StaffPane;

import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateHandler extends InsertHandler {
    public UpdateHandler(StaffPane context) {
        super(context);
    }

    @Override
    public void handle(MouseEvent event) {
        getForm().setMode(Form.Mode.UPDATE);

        String idStr = getForm().getId().getText();
        Staff staff = getStaff();

        if(staff == null) return;

        staff.setId(Integer.parseInt(idStr));

        try {
            staff = getRepository().update(staff);
            getForm().fill(staff);
            getMessageBox().success(MessageBox.MSG_STAFF_RECORD_UPDATED);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
