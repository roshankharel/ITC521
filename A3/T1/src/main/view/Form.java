package main.view;

import javafx.scene.control.TextField;
import main.entities.Staff;
import main.entities.State;

import java.math.BigInteger;

/**
 * Form
 * This class create all the form fields needed for Staff data insertion.
 * It provides necessary methods access those fields, filling those fields,
 * and resetting the fields to an empty state
 *
 * @author Roshan Kharel
 */
public class Form {
    /**
     * Various modes that this form can be in
     */
    public enum Mode {
        VIEW,
        INSERT,
        UPDATE,
        CLEAR;
    }

    /**
     * field names
     */
    public static final String FIELD_ID = "ID";
    public static final String FIELD_FIRST_NAME = "First Name";
    public static final String FIELD_MIDDLE_NAME = "Middle Name";
    public static final String FIELD_LAST_NAME = "Last Name";
    public static final String FIELD_ADDRESS = "Address";
    public static final String FIELD_CITY = "City";
    public static final String FIELD_STATE = "State";
    public static final String FIELD_TELEPHONE = "Telephone";

    /**
     * Initialization of the form fields
     */
    private final TextField idTextField = new TextField();
    private final TextField firstNameTextField = new TextField();
    private final TextField middleNameTextField = new TextField();
    private final TextField lastNameTextField = new TextField();
    private final TextField addressTextField = new TextField();
    private final TextField cityTextField = new TextField();
    private final TextField stateTextField = new TextField();
    private final TextField telephoneTextField = new TextField();
    private Mode mode = Mode.VIEW;

    /**
     * Gets the current mode of the form
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * Sets the current mode of the form
     */
    public void setMode(Mode mode) {
        idTextField.setEditable(mode == Mode.CLEAR);
        this.mode = mode;
    }

    /**
     * Gets the ID text field
     */
    public TextField getIdTextField() {
        return idTextField;
    }

    /**
     * Gets the First Name text field
     */
    public TextField getFirstNameTextField() {
        return firstNameTextField;
    }

    /**
     * Gets the Middle Name text field
     */
    public TextField getMiddleNameTextField() {
        return middleNameTextField;
    }

    /**
     * Gets the Last Name text field
     */
    public TextField getLastNameTextField() {
        return lastNameTextField;
    }

    /**
     * Gets the Address text field
     */
    public TextField getAddressTextField() {
        return addressTextField;
    }

    /**
     * Gets the City text field
     */
    public TextField getCityTextField() {
        return cityTextField;
    }

    /**
     * Gets the State text field
     */
    public TextField getStateTextField() {
        return stateTextField;
    }

    /**
     * Gets the telephone text field
     */
    public TextField getTelephoneTextField() {
        return telephoneTextField;
    }

    /**
     * Fills all the form fields based on supplied staff object
     */
    public void fill(Staff staff) {
        // String value of the fields
        // These can be empty string if staff object is null or
        // can be the actual value representing the staff's attributes
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

        // set the values for each text fields
        idTextField.setText(_id);
        firstNameTextField.setText(_firstName);
        middleNameTextField.setText(_middleName);
        lastNameTextField.setText(_lastName);
        addressTextField.setText(_address);
        cityTextField.setText(_city);
        stateTextField.setText(_state);
        telephoneTextField.setText(_telephone);
    }

    /**
     * Creates a Staff object based on the value of the all the text field
     */
    public Staff getStaff() {
        // initialize an default staff object
        Staff staff = new Staff();

        // set staff object's attributes according to the value from its corresponding text fields
        staff.setFirstName(firstNameTextField.getText().strip());
        staff.setMiddleName(middleNameTextField.getText().strip());
        staff.setLastName(lastNameTextField.getText().strip());
        staff.setAddress(addressTextField.getText().strip());
        staff.setCity(cityTextField.getText().strip());
        staff.setState(
                State.make(stateTextField.getText().strip())
        );

        staff.setTelephone(
                BigInteger.valueOf(
                        Long.parseLong(telephoneTextField.getText().strip())
                )
        );

        return staff;
    }

    /**
     * Resets the form fields to default (blank) state
     */
    public void reset() {
        fill(null);
    }
}
