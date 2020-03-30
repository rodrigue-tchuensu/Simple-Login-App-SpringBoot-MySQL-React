package de.tchuensu.home.springbootserver.web.exception;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }
}
