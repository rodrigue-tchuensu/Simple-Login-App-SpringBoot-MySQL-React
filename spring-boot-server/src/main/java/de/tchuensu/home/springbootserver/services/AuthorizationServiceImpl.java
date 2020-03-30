package de.tchuensu.home.springbootserver.services;

import de.tchuensu.home.springbootserver.dao.UserDao;
import de.tchuensu.home.springbootserver.web.exception.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    UserDao userDao;

    @Override
    public Long resourceAccessAuthorized(String authenticationToken) {

        if ( JWTTokenKeyTools.jwtIsOk(authenticationToken) ) {

            // We get the user-id of the user through which the jwt was generated
            Long userId = JWTTokenKeyTools.parseJWT(authenticationToken);

            // We check that the user trying to access the database is still present in our database.
            // Since we do not yet have accreditation levels, been present in our database means you
            // are authorized to access it.
            if(userDao.findUserById(userId) != null) {
                return userId;
            }
        }

        throw new ForbiddenException("Authorization not granted");
    }
}
