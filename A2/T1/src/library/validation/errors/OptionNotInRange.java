package library.validation.errors;

import library.Const;
import java.util.Arrays;

public class OptionNotInRange extends RuntimeException {
    private final String selectedOption;
    private final int[] options;

    /** Construct an exception */
    public OptionNotInRange(String selectedOption, int[] options) {
        super(String.format(Const.Message.Validation.IN_RANGE, Arrays.toString(options)));

        this.selectedOption = selectedOption;
        this.options = options;
    }

    /** Return invalid selected option */
    public String getSelectedOption() {
        return selectedOption;
    }

    /** Return available options */
    public int[] getOptions() {
        return options;
    }
}
