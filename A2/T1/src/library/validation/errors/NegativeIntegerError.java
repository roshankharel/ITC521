package library.validation.errors;

import library.Const;

public class NegativeIntegerError extends RuntimeException {
    private final String value;
    private final String fieldName;

    /** Construct an exception */
    public NegativeIntegerError(String value, String fieldName) {
        super(String.format(Const.Message.Validation.POSITIVE_INT, fieldName, value));
        this.value = value;
        this.fieldName = fieldName;
    }

    /** Return invalid value */
    public String getValue() {
        return value;
    }

    /** Return field name */
    public String getFieldName() {
        return fieldName;
    }
}
