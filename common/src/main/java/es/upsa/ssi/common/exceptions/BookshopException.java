package es.upsa.ssi.common.exceptions;

public class BookshopException extends Exception {

    public BookshopException() {
    }

    public BookshopException(String message) {
        super(message);
    }

    public BookshopException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookshopException(Throwable cause) {
        super(cause);
    }

    public BookshopException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
