package de.tchuensu.home.springbootserver.web.exception;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

public class UserCredentialException  extends RuntimeException {
    public UserCredentialException() {
        super();
    }

    public UserCredentialException(String message) {
        super(message);
    }

    public UserCredentialException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserCredentialException(Throwable cause) {
        super(cause);
    }
}
