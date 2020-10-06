package main.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import main.repositories.StaffRepository;
import main.view.Form;
import main.view.MessageBox;
import main.view.StaffPane;

public abstract class Handler implements EventHandler<MouseEvent> {
    StaffPane context;

    public Handler(StaffPane context) {
        this.context = context;
    }

    Form getForm() {
        return context.getForm();
    }

    StaffRepository getRepository() {
        return context.getStaffRepository();
    }

    MessageBox getMessageBox() {
        return context.getMessageBox();
    }
}
