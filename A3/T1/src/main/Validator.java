package main;

import main.entities.State;
import main.view.Form;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Validator The Validator class abstracts away the mechanism of validating various user inputs
 * required throughout the program.
 *
 * @author Roshan Kharel
 */
public class Validator {
    /**
     * Method validates that required field's value is not empty string. The method trims the value
     * before validation
     *
     * @param value     string to be validated
     * @param fieldName the name of the input field
     *
     * @throws RuntimeException if the length of supplied value is zero after trimming whitespace
     */
    public static void validateRequired(String value, String fieldName) throws RuntimeException {
        if (value.strip().length() == 0) {
            throw new RuntimeException(
                    String.format("%s field cannot be blank or only white-spaces.", fieldName)
            );
        }
    }

    /**
     * Method validates that supplied field's value is parsable to integer or BigInteger
     *
     * @param value     value to be validated
     * @param fieldName the name of the input field
     *
     * @throws NumberFormatException if the value is not an integer
     */
    public static void validateInteger(String value, String fieldName) throws NumberFormatException {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            try {
                //noinspection ResultOfMethodCallIgnored
                BigInteger.valueOf(Long.parseLong(value));
            } catch (NumberFormatException f) {
                throw new NumberFormatException(
                        String.format("%s field must be an integer", fieldName));
            }
        }
    }

    /**
     * Method validates that supplied field's value is parsable to integer or BigInteger and has
     * positive sign
     *
     * @param value     value to be validated
     * @param fieldName the name of the input field
     *
     * @throws RuntimeException if the value is not positive integer
     */
    public static void validatePositive(String value, String fieldName) throws RuntimeException {
        validateInteger(value, fieldName);

        BigInteger valueInt = BigInteger.valueOf(Long.parseLong(value));

        if (valueInt.signum() == -1) {
            throw new RuntimeException(
                    String.format("%s field must be a positive number.", fieldName)
            );
        }
    }

    /**
     * Method validates that supplied field's value is parsable to integer or BigInteger and has
     * positive sign
     *
     * @param value     value to be validated
     * @param fieldName the name of the input field
     *
     * @throws RuntimeException if the value is not positive integer
     */
    public static void validateGreaterThan(String value, int min, String fieldName) throws RuntimeException {
        validateInteger(value, fieldName);

        BigInteger valueInt = BigInteger.valueOf(Long.parseLong(value));
        BigInteger minVal = BigInteger.valueOf(min);

        if (valueInt.compareTo(minVal) < 0) {
            throw new RuntimeException(
                    String.format("%s field must be greater or equal to %d.", fieldName, min)
            );
        }
    }

    /**
     * Method validates if the value is within the provided array of integers
     *
     * @param value     input to be validated
     * @param fieldName name of the field
     *
     * @throws RuntimeException if the value is not in the options array
     */
    public static void validateState(String value, String fieldName)
            throws RuntimeException {

        if (State.make(value) == null) {
            throw new RuntimeException(
                    String.format("%s field value cannot be other than %s", fieldName,
                            String.join(", ", State.getKeys()))
            );
        }
    }

    /**
     * Method validates all the form fields based of the current mode of the form
     *
     * @param form Form represents a staff information typed by user
     *
     * @return A list of errors after validating the form. Empty list indicates the form has no
     *         error.
     *
     * @throws RuntimeException if the form mode is view and the validated field yield's error
     */
    public static ArrayList<String> validateForm(Form form) {
        // check if form is in view mode
        if (form.getMode() == Form.Mode.VIEW) {
            // valid only required fields in view mode
            validateViewForm(form);
            return new ArrayList<>();
        }

        // validate all the fields in insert and update mode
        return validateInsertUpdateForm(form);
    }

    /**
     * Method validates only the id field of the form
     *
     * @param form Form represents a staff information typed by user
     *
     * @throws RuntimeException the id is invalid
     */
    private static void validateViewForm(Form form) {
        String id = form.getIdTextField().getText();

        Validator.validateRequired(id, Form.FIELD_ID);
        Validator.validateGreaterThan(id, 1, Form.FIELD_ID);
    }

    /**
     * Method validates all the fields of the form
     *
     * @param form Form represents a staff information typed by user
     *
     * @return A list of errors after validating the form. Empty list indicates the form has no
     *         error.
     */
    private static ArrayList<String> validateInsertUpdateForm(Form form) {
        // initialize empty list to hold fields' errors
        ArrayList<String> errors = new ArrayList<>();

        // get string representation of staff data from the supplied form
        String fName = form.getFirstNameTextField().getText();
        String mName = form.getMiddleNameTextField().getText();
        String lName = form.getLastNameTextField().getText();
        String address = form.getAddressTextField().getText();
        String city = form.getCityTextField().getText();
        String state = form.getStateTextField().getText();
        String telephone = form.getTelephoneTextField().getText();

        // validate first name and add error if exists
        try {
            validateRequired(fName, Form.FIELD_FIRST_NAME);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }

        // validate middle name and add error if exists
        try {
            validateRequired(mName, Form.FIELD_MIDDLE_NAME);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }

        // validate last name and add error if exists
        try {
            validateRequired(lName, Form.FIELD_LAST_NAME);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }

        // validate address and add error if exists
        try {
            validateRequired(address, Form.FIELD_ADDRESS);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }

        // validate city and add error if exists
        try {
            validateRequired(city, Form.FIELD_CITY);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }

        // validate state and add error if exists
        try {
            validateRequired(state, Form.FIELD_STATE);
            validateState(state, Form.FIELD_STATE);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }

        // validate telephone and add error if exists
        try {
            validateRequired(telephone, Form.FIELD_TELEPHONE);
            validateInteger(telephone, Form.FIELD_TELEPHONE);
            validatePositive(telephone, Form.FIELD_TELEPHONE);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }

        return errors;
    }
}
