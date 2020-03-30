package de.tchuensu.home.springbootserver.services;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

public interface AuthorizationService {

    /**
     *
     * @param authenticationToken
     * @return
     */
    Long resourceAccessAuthorized(String authenticationToken);
}
