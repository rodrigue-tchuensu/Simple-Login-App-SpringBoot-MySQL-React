package de.tchuensu.home.springbootserver.web.api;


import de.tchuensu.home.springbootserver.dao.UserDao;
import de.tchuensu.home.springbootserver.dao.dto.UserCredentialsDto;
import de.tchuensu.home.springbootserver.model.User;
import de.tchuensu.home.springbootserver.services.JWTTokenKeyTools;
import de.tchuensu.home.springbootserver.services.PasswordEncryptorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/authentication")
public class AuthenticationController {

    private UserDao userDao;
    private PasswordEncryptorManager passwordEncryptorManager;

    @Autowired
    public AuthenticationController(UserDao userDao, PasswordEncryptorManager passwordEncryptorManager) {
        this.userDao = userDao;
        this.passwordEncryptorManager = passwordEncryptorManager;
    }
    @PostMapping
    public ResponseEntity<String> authenticateUser (@RequestBody UserCredentialsDto userCredentials) {

        // We check that the body of the request is available
        if(userCredentials.getUsername() == null || userCredentials.getPassword() == null){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        // We check that the user trying to authenticate himself is present in our database
        User user = userDao.findUserByUsername(userCredentials.getUsername());

        // If the user is not found in our database then we do not generate a jwt and
        // the server returns an unauthorized http status response.
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        //we have to validate the users'  password
        boolean passwordValidated = passwordEncryptorManager.checkPassword(userCredentials.getPassword(), user.getPasswordHash());

        if(!passwordValidated) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        //if the user is successfully authenticated we generate a jwt using a utility method
        String generatedJWT = JWTTokenKeyTools.createJWT(user.getId(), 3600000);

        //we return the generated jwt in the response as string
        return new ResponseEntity<>(generatedJWT, HttpStatus.OK);
    }
}
