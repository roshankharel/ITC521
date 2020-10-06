package main.handlers;

import javafx.scene.input.MouseEvent;
import main.view.Form;
import main.view.MessageBox;
import main.view.StaffPane;

public class ClearHandler extends Handler {
    public ClearHandler(StaffPane context) {
        super(context);
    }

    @Override
    public void handle(MouseEvent event) {
        getForm().setMode(Form.Mode.CLEAR);
        getMessageBox().info(MessageBox.MSG_GREET);
        getForm().fill(null);
    }
}
