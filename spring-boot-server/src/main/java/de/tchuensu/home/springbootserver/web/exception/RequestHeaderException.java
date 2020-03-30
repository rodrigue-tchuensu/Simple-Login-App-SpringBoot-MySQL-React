package de.tchuensu.home.springbootserver.web.exception;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

public class RequestHeaderException extends RuntimeException {
    public RequestHeaderException() {
        super();
    }

    public RequestHeaderException(String message) {
        super(message);
    }

    public RequestHeaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestHeaderException(Throwable cause) {
        super(cause);
    }
}
