package library.validation;

import library.Const;
import library.validation.errors.*;

import java.math.BigInteger;

/**
 * Validator
 * The Validator class abstracts away the mechanism of validating various
 * user inputs required throughout the program.
 *
 * @author Roshan Kharel
 */
public class Validator {
    /**
     * Method validates that required field's value is not empty string.
     * The method trims the value before validation
     *
     * @param value string to be validated
     * @param fieldName the name of the input field
     *
     * @throws FieldRequiredError if the length of supplied value is zero after trimming whitespace
     */
    public static void validateRequired(String value, String fieldName) throws FieldRequiredError {
        if (value.strip().length() == 0) {
            throw new FieldRequiredError(fieldName);
        }
    }

    /**
     * Method validates that supplied field's value is parsable to integer or BigInteger
     *
     * @param value value to be validated
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
                        String.format(Const.Message.Validation.INT, fieldName, value));
            }
        }
    }

    /**
     * Method validates that supplied field's value is parsable to integer or BigInteger
     * and has positive sign
     *
     * @param value value to be validated
     * @param fieldName the name of the input field
     *
     * @throws RuntimeException if the value is not positive integer
     */
    public static void validatePositive(String value, String fieldName) throws NegativeIntegerError {
        validateInteger(value, fieldName);

        BigInteger valueInt = BigInteger.valueOf(Long.parseLong(value));

        if (valueInt.signum() == -1) {
            throw new NegativeIntegerError(value, fieldName);
        }
    }

    /**
     * Method validates that supplied field's value is an ISBN number
     *
     * @param isbn isbn number to be validated
     *
     * @throws RuntimeException if the value is not 10 digit positive ISBN number
     */
    public static void validateISBN(String isbn, String fieldName)
            throws ISBNFormatError, FieldRequiredError, NumberFormatException, NegativeIntegerError {
        validateRequired(isbn, fieldName);
        validatePositive(isbn, fieldName);

        if (isbn.trim().length() != 10) {
            throw new ISBNFormatError(isbn, fieldName);
        }
    }

    /**
     * Method validates that supplied field's value is a 4-digit number
     *
     * @param year year to be validated
     * @throws RuntimeException if the value is not 4 digit positive number
     */
    public static void validateYear(String year, String fieldName)
            throws YearFormatError, FieldRequiredError, NumberFormatException {
        validateRequired(year, fieldName);
        validatePositive(year, fieldName);

        if (BigInteger.valueOf(Long.parseLong(year)).toString().length() != 4) {
            throw new YearFormatError(year, fieldName);
        }
    }

    /**
     * Method validates if the value is within the provided array of integers
     *
     * @param value input to be validated
     * @param options list of available options
     *
     * @throws RuntimeException if the value is not in the options array
     */
    public static void validateInOptions(String value, int[] options, String fieldName)
            throws NumberFormatException, OptionNotInRange {
        validateRequired(value, fieldName);
        validateInteger(value, fieldName);

        int input = Integer.parseInt(value);

        for (int option : options) if (option == input) return;

        throw new OptionNotInRange(value, options);
    }
}
