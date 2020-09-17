package library.validation.errors;

import library.Const;

public class ISBNFormatError extends RuntimeException {
    private final String value;
    private final String fieldName;

    /** Construct an exception */
    public ISBNFormatError(String value, String fieldName) {
        super(String.format(Const.Message.Validation.ISBN, fieldName, value));
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
