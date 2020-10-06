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

    public static ArrayList<String> validateForm(Form form) {
        if (form.getMode() == Form.Mode.VIEW) {
            validateViewForm(form);
            return new ArrayList<>();
        }

        return validateInsertUpdateForm(form);
    }

    private static void validateViewForm(Form form) {
        String id = form.getId().getText();

        Validator.validateRequired(id, Form.FIELD_ID);
        Validator.validateGreaterThan(id, 1, Form.FIELD_ID);
    }

    private static ArrayList<String> validateInsertUpdateForm(Form form) {
        ArrayList<String> errors = new ArrayList<>();
        String fName = form.getFirstName().getText();
        String mName = form.getMiddleName().getText();
        String lName = form.getLastName().getText();
        String address = form.getAddress().getText();
        String city = form.getCity().getText();
        String state = form.getState().getText();
        String telephone = form.getTelephone().getText();

        try {
            validateRequired(fName, Form.FIELD_FIRST_NAME);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }

        try {
            validateRequired(mName, Form.FIELD_MIDDLE_NAME);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }

        try {
            validateRequired(lName, Form.FIELD_LAST_NAME);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }

        try {
            validateRequired(address, Form.FIELD_ADDRESS);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }

        try {
            validateRequired(city, Form.FIELD_CITY);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }

        try {
            validateRequired(state, Form.FIELD_STATE);
            validateState(state, Form.FIELD_STATE);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }

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
