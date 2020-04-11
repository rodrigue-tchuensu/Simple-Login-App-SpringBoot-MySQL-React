package de.tchuensu.home.springbootserver.util;

import de.tchuensu.home.springbootserver.web.exception.RequestHeaderException;
import de.tchuensu.home.springbootserver.web.exception.ResourceNotFoundException;
import de.tchuensu.home.springbootserver.web.exception.UserCredentialException;
import org.springframework.web.bind.MissingPathVariableException;


/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

public class RestPreconditions {
    private RestPreconditions() {
        throw new AssertionError();
    }

    // API

    /**
     * Check if some value was found, otherwise throw exception.
     *
     * @param expression
     *            has value true if found, otherwise false
     * @throws ResourceNotFoundException
     *             if expression is false, means value not found.
     */
    public static void checkFound(final boolean expression) {
        if (!expression) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Check if some value was found, otherwise throw exception.
     *
     * @param resource
     *            has value true if found, otherwise false
     * @throws ResourceNotFoundException
     *             if expression is false, means value not found.
     */
    public static <T> T checkFound(final T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException();
        }

        return resource;
    }

    /**
     *
     *
     * @param resource
     * @param errorMessage
     * @param <T>
     * @return
     */
    public static <T> T checkFound(final T resource, String errorMessage) {
        if (resource == null) {
            throw new ResourceNotFoundException(errorMessage);
        }

        return resource;
    }


    /**
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> T checkCredentialNotNull(final T obj) {
        if (obj == null) {
            throw new UserCredentialException();
        }

        return obj;
    }

    /**
     *
     * @param obj
     * @param errorMessage
     * @param <T>
     * @return
     */
    public static <T> T checkCredentialNotNull(final T obj, String errorMessage) {
        if (obj == null) {
            throw new UserCredentialException(errorMessage);
        }

        return obj;
    }

    public static  <T> T checkRequestHeaderNotNull (final T obj) {
        if (obj == null) {
            throw new RequestHeaderException();
        }

        return obj;
    }

    public static  <T> T checkRequestHeaderNotNull (final T obj, String errorMessage) {
        if (obj == null) {
            throw new RequestHeaderException(errorMessage);
        }

        return obj;
    }

    /*
    public static  <T> T checkPathVariableNotNull (final T obj) {
        if (obj == null) {
            throw new MissingPathVariableException;
        }

        return obj;
    }

    public static  <T> T checkPathVariableNotNull (final T obj, String errorMessage) {
        if (obj == null) {
            throw new MissingPathVariableException();
        }

        return obj;
    }

     */
}
