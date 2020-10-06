package main.handlers;

import javafx.scene.input.MouseEvent;
import main.Validator;
import main.entities.Staff;
import main.view.Form;
import main.view.MessageBox;
import main.view.StaffPane;

import java.sql.SQLException;

public class ViewHandler extends Handler {
    public ViewHandler(StaffPane context) {
        super(context);
    }

    @Override
    public void handle(MouseEvent event) {
        String idStr = getForm().getId().getText();
        getForm().setMode(Form.Mode.VIEW);

        try {
            getForm().reset();
            getForm().getId().setText(idStr);
            Validator.validateForm(getForm());

            int id = Integer.parseInt(idStr.strip());
            Staff staff = getRepository().find(id);
            getForm().fill(staff);
            getMessageBox().success(MessageBox.MSG_STAFF_RECORD_FOUND);
        } catch (RuntimeException error) {
            getMessageBox().error(error.getMessage());
            getForm().setMode(Form.Mode.CLEAR);
            error.printStackTrace();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            getForm().setMode(Form.Mode.CLEAR);
        }
    }
}
