package library.validation.errors;

import library.Const;

public class YearFormatError extends RuntimeException {
    private final String fieldName;
    private final String value;

    /** Construct an exception */
    public YearFormatError(String value, String fieldName) {
        super(String.format(Const.Message.Validation.YEAR, fieldName, value));
        this.value = value;
        this.fieldName = fieldName;
    }

    /** Return field name */
    public String getFieldName() {
        return fieldName;
    }

    /** Return invalid value */
    public String getValue() {
        return value;
    }
}