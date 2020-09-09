package library;

import java.util.Arrays;
import java.math.BigInteger;

import library.Const.Message.Validation;

public class InputValidator {
    /**
     * Check if supplied value is not empty after trimming whitespaces
     * and shows error message if the validation fails
     *
     * @param value string to be validated
     * @param fieldName the name of the input field
     *
     * @return boolean value of the validation
     */
    public static boolean validateRequired(String value, String fieldName) {
        boolean isValid = value.strip().length() > 0;

        if (!isValid)
            System.out.printf((Validation.REQUIRED) + "%n", fieldName);

        return isValid;
    }

    /**
     * Check if supplied value is a positive integer
     * and shows error message if the validation fails
     *
     * @param value integer to be validated
     * @param fieldName the name of the input field
     *
     * @return boolean value of the validation
     */
    public static boolean validatePositive(int value, String fieldName) {
        boolean isValid = value > -1;

        if (!isValid)
            System.out.printf((Validation.POSITIVE_INT) + "%n", fieldName);

        return isValid;
    }

    /**
     * Check if supplied value is a positive BigInteger
     * and shows error message if the validation fails
     *
     * @param value a BigInteger to be validated
     * @param fieldName the name of the input field
     *
     * @return boolean value of the validation
     */
    public static boolean validatePositive(BigInteger value, String fieldName) {
        boolean isValid = value.abs().equals(value);

        if (!isValid)
            System.out.printf((Validation.POSITIVE_INT) + "%n", fieldName);

        return isValid;
    }

    /**
     * Check if supplied value is an integer
     * and shows error message if the validation fails
     *
     * @param value value to be validated
     * @param fieldName the name of the input field
     *
     * @return boolean value of the validation
     */
    public static boolean validateInteger(String value, String fieldName) {
        boolean isValid = false;

        try {
            Integer.parseInt(value);
            isValid = true;
        } catch (NumberFormatException e) {
            System.out.printf((Validation.INT) + "%n", fieldName);
        }

        return isValid;
    }

    /**
     * Check if supplied value is a BigInteger
     * and shows error message if the validation fails
     *
     * @param value value to be validated
     * @param fieldName the name of the input field
     *
     * @return boolean value of the validation
     */
    public static boolean validateBigInteger(String value, String fieldName) {
        boolean isValid = false;

        try {
            //noinspection ResultOfMethodCallIgnored
            BigInteger.valueOf(Long.parseLong(value));
            isValid = true;
        } catch (NumberFormatException e) {
            System.out.printf((Validation.INT) + "%n", fieldName);
        }

        return isValid;
    }

    /**
     * Check if supplied value is a valid ISBN number
     * and shows error message if the validation fails
     *
     * @param value value to be validated
     * @param fieldName the name of the input field
     *
     * @return boolean value of the validation
     */
    public static boolean validateISBNLength(BigInteger value, String fieldName) {
        int length = value.toString().length();
        boolean isValid = length <= 10 || length == 13;

        if (!isValid)
            System.out.printf((Validation.ISBN) + "%n", fieldName);

        return isValid;
    }

    /**
     * Check if supplied value is of desired length
     * and shows error message if the validation fails
     *
     * @param value value to be validated
     * @param length expected length of value
     * @param fieldName the name of the input field
     *
     * @return boolean value of the validation
     */
    public static boolean validateLength(int value, int length, String fieldName) {
        boolean isValid = String.valueOf(value).length() == length;

        if (!isValid)
            System.out.printf((Validation.DEFINED_LENGTH) + "%n", fieldName, length);

        return isValid;
    }

    /**
     * Check if supplied value exists in the provided array of options
     * and shows error message if the validation fails
     *
     * @param input input to be validated
     * @param options list of available options
     *
     * @return boolean value of the validation
     */
    public static boolean validateInArray(int input, int[] options) {
        for (int option : options) if (option == input) return true;

        System.out.printf((Validation.IN_RANGE) + "%n", Arrays.toString(options));

        return false;
    }
}
