package de.tchuensu.home.springbootserver.web.api;


import de.tchuensu.home.springbootserver.dao.UserDao;
import de.tchuensu.home.springbootserver.model.User;
import de.tchuensu.home.springbootserver.services.JWTTokenKeyTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserEndpoint {

    private UserDao userDao;

    @Autowired
    public UserEndpoint(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping(value= "user")
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String authenticationToken) {

        // We check that the JWT is valid
        if (!JWTTokenKeyTools.jwtIsOk(authenticationToken)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // We get the user-id of the user throw which the jwt was generated
        Long userId = JWTTokenKeyTools.parseJWT(authenticationToken);

        // We check that the user is stored in our database
        User user =  userDao.findUserById(userId);

        // If the user  was deleted but the authentication token wasn't removed.
        // then the request cannot return the users' object representing it's details
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping(value= "users")
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String authenticationToken) {

        // We check that the JWT is valid
        if (!JWTTokenKeyTools.jwtIsOk(authenticationToken)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // We get the user-id of the user throw which the jwt was generated
        Long userId = JWTTokenKeyTools.parseJWT(authenticationToken);

        // We check that the user is still recorded in our database
        User user =  userDao.findUserById(userId);

        // If the user  was deleted but the authentication token wasn't removed.
        // then the request cannot return the list of users because the user is
        // not more allowed to issue this request
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        //we get the list of users
        List<User> usersList = userDao.findAll();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }


}
