package de.tchuensu.home.springbootserver.web.api;


import de.tchuensu.home.springbootserver.dao.UserDao;
import de.tchuensu.home.springbootserver.model.User;
import de.tchuensu.home.springbootserver.services.AuthorizationServiceImpl;
import de.tchuensu.home.springbootserver.services.JWTTokenKeyTools;
import de.tchuensu.home.springbootserver.services.PasswordEncryptorManager;
import de.tchuensu.home.springbootserver.services.UserServiceImpl;
import de.tchuensu.home.springbootserver.util.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    AuthorizationServiceImpl authorizationService;

    private UserDao userDao;
    private PasswordEncryptorManager passwordEncryptorManager;

    @Autowired
    public UserController(UserDao userDao, PasswordEncryptorManager passwordEncryptorManager) {
        this.userDao = userDao;
        this.passwordEncryptorManager = passwordEncryptorManager;
    }


    @GetMapping(value= "user")
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String authenticationToken) {

        RestPreconditions.checkRequestHeaderNotNull(authenticationToken, "Authorization header Missing");

        //We check that the user trying to issue this required is registered in our database.
        //If the user is found to be registered in the database the userId is returned.
        //If it is not the case this method throws a ForbiddenException
        Long userId = authorizationService.resourceAccessAuthorized(authenticationToken);
        User user = userService.findUserById(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping(value= "users")
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String authenticationToken) {

        RestPreconditions.checkRequestHeaderNotNull(authenticationToken, "Authorization header Missing");

        //We check that the user trying to issue this required is registered in our database.
        //If the user is found to be registered in the database the userId is returned.
        //If it is not the case this method throws a ForbiddenException
        authorizationService.resourceAccessAuthorized(authenticationToken);

        //we get the list of users
        List<User> usersList = userService.findAllUsers();

        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }


}
