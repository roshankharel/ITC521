package library.validation;

import library.Const;
import library.validation.errors.*;

import java.math.BigInteger;

public class Validator {
    /**
     * Method validated that required field's value is not empty string.
     * The method trims the value before validation
     *
     * @param value
     *              string to be validated
     * @param fieldName the name of the input field
     * @throws FieldRequiredError if the length of supplied value is zero after trimming whitespace
     */
    public static void validateRequired(String value, String fieldName) throws FieldRequiredError {
        if (value.strip().length() == 0) {
            throw new FieldRequiredError(fieldName);
        }
    }

    /**
     * Method validated that required field's value is parsable to integer
     *
     * @param value value to be validated
     * @param fieldName the name of the input field
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
     * Check if supplied value is a positive integer and throw error if the validation fails
     *
     * @param value value to be validated
     * @param fieldName the name of the input field
     */
    public static void validatePositive(String value, String fieldName) throws NegativeIntegerError {
        validateInteger(value, fieldName);

        BigInteger valueInt = BigInteger.valueOf(Long.parseLong(value));

        if (valueInt.signum() == -1) {
            throw new NegativeIntegerError(value, fieldName);
        }
    }

    /**
     * Check if supplied value is a valid ISBN number and throw error when validation fails
     *
     * @param isbn isbn number to be validated
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
     * Check if supplied value is a valid year and throw error when validation fails
     *
     * @param year year to be validated
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
     * Check if supplied value exists in the provided array of options and shows error message if the
     * validation fails
     *
     * @param value input to be validated
     * @param options list of available options
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
