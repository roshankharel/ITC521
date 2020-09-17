package library;

import library.validation.Validator;
import library.validation.errors.FieldRequiredError;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * KeyboardInput The KeyboardInput class simply provides methods get predefined types of input that
 * are guaranteed to be valid.
 *
 * @author Roshan Kharel
 */
public class KeyboardInput {

    /** The "standard" input stream. This stream is already * open and ready to supply input data */
    public static final Scanner Keyboard = new Scanner(System.in);

    /**
     * Ask user for book title
     *
     * @return title of the book
     */
    public static String getTitle() {
        String value;
        boolean isValid = false;

        // ask for the book title
        do {
            System.out.print("Title of the book: ");
            value = Keyboard.nextLine().trim();

            try {
                Validator.validateRequired(value, Const.FieldName.TITLE);
                isValid = true;
            } catch (FieldRequiredError e) {
                System.out.println(e.getMessage());
            }
        } while (!isValid);

        return value;
    }

    /**
     * Ask user for book author
     *
     * @return author of the book
     */
    public static String getAuthor() {
        String value;
        boolean isValid = false;

        // ask for the book author
        do {
            System.out.print("Author of the book: ");
            value = Keyboard.nextLine().trim();

            try {
                Validator.validateRequired(value, Const.FieldName.AUTHOR);
                isValid = true;
            } catch (FieldRequiredError e) {
                System.out.println(e.getMessage());
            }

        } while (!isValid);

        return value;
    }

    /**
     * Ask user for book published year
     *
     * @return published year of the book
     */
    public static int getYear() {
        String value;
        boolean isValid = false;

        // ask for the book published year
        do {
            System.out.print("Year of publication: ");
            value = Keyboard.nextLine().trim();

            try {
                Validator.validateYear(value, Const.FieldName.PUBLISHED_YEAR);
                isValid = true;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        } while (!isValid);

        return Integer.parseInt(value);
    }

    /**
     * Ask user for book ISBN number
     *
     * @return ISBN of the book
     */
    public static BigInteger getISBN() {
        String value;
        boolean isValid = false;

        // ask for the book isbn
        do {
            System.out.print("ISBN number: ");
            value = Keyboard.nextLine().trim();

            try {
                Validator.validateISBN(value, Const.FieldName.ISBN);
                isValid = true;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        } while (!isValid);

        return BigInteger.valueOf(Long.parseLong(value));
    }

    /**
     * Ask user to make choice from given options
     *
     * @param options array of options to choose from
     * @param ask question for the console prompt
     * @param fieldName name of the input field
     * @return choice from the options
     */
    protected static int getFromOptions(int[] options, String ask, String fieldName) {
        String value;
        boolean isValid = false;

        // ask for the choice
        do {
            System.out.printf("%s: ", ask);
            value = Keyboard.nextLine().trim();

            try {
                Validator.validateInOptions(value, options, fieldName);
                isValid = true;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        } while (!isValid);

        return Integer.parseInt(value);
    }

    /**
     * Ask user to make choice from given options
     *
     * @param options array of options to choose from
     * @return choice from the options
     */
    public static int getMenuOptionId(int[] options) {
        return getFromOptions(options, "Enter a menu option", Const.FieldName.OPTION);
    }

    /**
     * Ask user to make choice from given books
     *
     * @param options array of book index to choose from
     * @return chosen book index
     */
    public static int getBookNumber(int[] options) {
        return getFromOptions(options, "Enter a Book number", Const.FieldName.BOOK_NUMBER);
    }
}
