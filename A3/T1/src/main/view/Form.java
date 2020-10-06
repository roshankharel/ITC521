package main.view;

import javafx.scene.control.TextField;
import main.entities.Staff;
import main.entities.State;

import java.math.BigInteger;

public class Form {
    public enum Mode {
        VIEW,
        INSERT,
        UPDATE,
        CLEAR;
    }

    public static final String FIELD_ID = "ID";
    public static final String FIELD_FIRST_NAME = "First Name";
    public static final String FIELD_MIDDLE_NAME = "Middle Name";
    public static final String FIELD_LAST_NAME = "Last Name";
    public static final String FIELD_ADDRESS = "Address";
    public static final String FIELD_CITY = "City";
    public static final String FIELD_STATE = "State";
    public static final String FIELD_TELEPHONE = "Telephone";

    private final TextField id = new TextField();
    private final TextField firstName = new TextField();
    private final TextField middleName = new TextField();
    private final TextField lastName = new TextField();
    private final TextField address = new TextField();
    private final TextField city = new TextField();
    private final TextField state = new TextField();
    private final TextField telephone = new TextField();
    private Mode mode = Mode.VIEW;

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        id.setEditable(mode == Mode.CLEAR);
        this.mode = mode;
    }

    public TextField getId() {
        return id;
    }

    public TextField getFirstName() {
        return firstName;
    }

    public TextField getMiddleName() {
        return middleName;
    }

    public TextField getLastName() {
        return lastName;
    }

    public TextField getAddress() {
        return address;
    }

    public TextField getCity() {
        return city;
    }

    public TextField getState() {
        return state;
    }

    public TextField getTelephone() {
        return telephone;
    }

    public void fill(Staff staff) {
        System.out.println(staff);

        String _id = staff != null ? String.valueOf(staff.getId()) : "";
        String _firstName = staff != null ? staff.getFirstName() : "";
        String _middleName = staff != null ? staff.getMiddleName() : "";
        String _lastName = staff != null ? staff.getLastName() : "";
        String _address = staff != null ? staff.getAddress() : "";
        String _city = staff != null ? staff.getCity() : "";
        String _state = staff != null && staff.getState() != null ? staff.getState().toString() :
                                "";
        String _telephone = staff != null && staff.getTelephone() != null ?
                                staff.getTelephone().toString() : "";

        id.setText(_id);
        firstName.setText(_firstName);
        middleName.setText(_middleName);
        lastName.setText(_lastName);
        address.setText(_address);
        city.setText(_city);
        state.setText(_state);
        telephone.setText(_telephone);
    }

    public Staff getStaff() {
        Staff staff = new Staff();

        staff.setFirstName(firstName.getText().strip());
        staff.setMiddleName(middleName.getText().strip());
        staff.setLastName(lastName.getText().strip());
        staff.setAddress(address.getText().strip());
        staff.setCity(city.getText().strip());
        staff.setState(
                State.make(state.getText().strip())
        );

        staff.setTelephone(
                BigInteger.valueOf(
                        Long.parseLong(telephone.getText().strip())
                )
        );

        return staff;
    }

    public void reset() {
        fill(null);
    }
}
