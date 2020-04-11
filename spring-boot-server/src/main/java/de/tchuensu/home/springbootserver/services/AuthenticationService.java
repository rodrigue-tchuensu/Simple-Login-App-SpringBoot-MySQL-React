package de.tchuensu.home.springbootserver.services;

import de.tchuensu.home.springbootserver.dao.dto.model.AthenticationData;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

public interface AuthenticationService {

    /**
     *
     * @param userCredentials
     * @return
     */
    String authenticateUser(AthenticationData userCredentials);
}
