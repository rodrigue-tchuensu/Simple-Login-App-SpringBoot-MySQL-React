package de.tchuensu.home.springbootserver.services;

import de.tchuensu.home.springbootserver.dao.UserDao;
import de.tchuensu.home.springbootserver.dao.dto.UserCredentialsDto;
import de.tchuensu.home.springbootserver.model.User;
import de.tchuensu.home.springbootserver.web.exception.ForbiddenException;
import de.tchuensu.home.springbootserver.web.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordEncryptorManager passwordEncryptorManager;


    /**
     *
     * @param userCredentials
     * @return
     */
    @Override
    public String authenticateUser(UserCredentialsDto userCredentials) {
        User user;
        try {
            user = userService.findUserByUsername(userCredentials.getUsername() );
        } catch (ResourceNotFoundException ex) {
            throw new ForbiddenException(ex.getMessage() + "\nAuthentication Failed !");
        }
        if( passwordEncryptorManager.checkPassword(userCredentials.getPassword(), user.getPasswordHash() ) ) {
            return JWTTokenKeyTools.createJWT(user.getId(), 3600000);
        }
        throw new ForbiddenException("Authentication Failed !");
    }
}
