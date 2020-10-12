package main.errors;

/**
 * CRUDError
 *
 * A runtime error indicating database Create, Read, Update, or Delete failure
 */
public class CRUDError extends RuntimeException{
    private final String sql;

    public CRUDError(String message, String sql) {
        super(message);
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
