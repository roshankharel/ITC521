package library;

import java.math.BigInteger;
import java.util.function.Function;

public class KeyboardInput {
    public static final String FieldTitle = "Title";
    public static final String FieldAuthor = "Author";
    public static final String FieldYear = "Published Year";
    public static final String FieldISBN = "ISBN";
    public static final String FieldSearchCriteria = "Search Criteria";
    public static final String FieldMenuOption = "Option";
    public static final String FieldBookNumber = "Book Number";

    /**
     * Ask user for book title
     *
     * @return title of the book
     */
    public static String getTitle() {
        String value;

        // ask for the book title
        do {
            System.out.print("Title of the book: ");
            value = Application.Keyboard.nextLine().trim();
        } while (!InputValidator.validateRequired(value, FieldTitle));

        return value;
    }

    /**
     * Ask user for book author
     *
     * @return author of the book
     */
    public static String getAuthor() {
        String value;

        // ask for the book author
        do {
            System.out.print("Author of the book: ");
            value = Application.Keyboard.nextLine().trim();
        } while (!InputValidator.validateRequired(value, FieldAuthor));

        return value;
    }

    /**
     * Ask user for book published year
     *
     * @return published year of the book
     */
    public static int getYear() {
        String value;

        Function<String, Boolean> isValid =
                (String input) ->
                        InputValidator.validateRequired(input, FieldYear)
                                && InputValidator.validateInteger(input, FieldYear)
                                && InputValidator.validatePositive(Integer.parseInt(input), FieldYear)
                                && InputValidator.validateLength(Integer.parseInt(input), 4, FieldYear);

        // ask for the book published year
        do {
            System.out.print("Year of publication: ");
            value = Application.Keyboard.nextLine().trim();
        } while (!isValid.apply(value));

        return Integer.parseInt(value);
    }

    /**
     * Ask user for book ISBN number
     *
     * @return ISBN of the book
     */
    public static BigInteger getISBN() {
        String value;

        Function<String, Boolean> isValid =
                (String input) -> {
                    if (!InputValidator.validateRequired(input, FieldISBN)
                            || !InputValidator.validateBigInteger(input, FieldISBN)) return false;

                    BigInteger isbn = BigInteger.valueOf(Long.parseLong(input));

                    return InputValidator.validatePositive(isbn, FieldISBN)
                            && InputValidator.validateISBNLength(isbn, FieldISBN);
                };

        // ask for the book isbn
        do {
            System.out.print("ISBN number: ");
            value = Application.Keyboard.nextLine().trim();
        } while (!isValid.apply(value));

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

        Function<String, Boolean> isValid =
                (String input) ->
                        InputValidator.validateRequired(input, fieldName)
                                && InputValidator.validateInteger(input, fieldName)
                                && InputValidator.validateInArray(Integer.parseInt(input), options);

        // ask for the choice
        do {
            System.out.printf("%s: ", ask);
            value = Application.Keyboard.nextLine().trim();
        } while (!isValid.apply(value));

        return Integer.parseInt(value);
    }

    /**
     * Ask user to make choice from given options
     *
     * @param options array of options to choose from
     * @return choice from the options
     */
    public static int getSearchByCriteria(int[] options) {
        return getFromOptions(options, "Choose search-by criteria", FieldSearchCriteria);
    }

    /**
     * Ask user to make choice from given options
     *
     * @param options array of options to choose from
     * @return choice from the options
     */
    public static int getMenuOptionId(int[] options) {
        return getFromOptions(options, "Enter a menu option", FieldMenuOption);
    }

    /**
     * Ask user to make choice from given books
     *
     * @param options array of book index to choose from
     * @return chosen book index
     */
    public static int getBookNumber(int[] options) {
        return getFromOptions(options, "Enter a Book number", FieldBookNumber);
    }
}
