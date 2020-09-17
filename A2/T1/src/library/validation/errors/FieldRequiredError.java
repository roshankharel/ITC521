package library.validation.errors;

import library.Const;

public class FieldRequiredError extends RuntimeException {
    private final String fieldName;

    /** Construct an exception */
    public FieldRequiredError(String fieldName) {
        super(String.format(Const.Message.Validation.REQUIRED, fieldName));
        this.fieldName = fieldName;
    }

    /** Return field name */
    public String getFieldName() {
        return fieldName;
    }
}
