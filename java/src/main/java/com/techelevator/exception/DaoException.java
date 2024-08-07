package com.techelevator.exception;

public class DaoException extends RuntimeException {
    public static String NO_DATABASE_ERROR = "Unable to connect to server or database";
    public static String DATA_INTEGRITY_ERROR = "Data integrity violation";
    public static String BADLY_FORMED_ERROR = "Data query badly formed";
    public static String NO_DATA_AFFECTED = "Expected to change some data, but none was affected";

    public DaoException() {
        super();
    }
    public DaoException(String message) {
        super(message);
    }
    public DaoException(String message, Exception cause) {
        super(message, cause);
    }
}
