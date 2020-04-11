package de.tchuensu.home.springbootserver.web.api;


import de.tchuensu.home.springbootserver.dao.dto.model.UserDto;
import de.tchuensu.home.springbootserver.services.AuthorizationServiceImpl;
import de.tchuensu.home.springbootserver.services.UserServiceImpl;
import de.tchuensu.home.springbootserver.util.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    AuthorizationServiceImpl authorizationService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestHeader("Authorization") String authenticationToken) {

        RestPreconditions.checkRequestHeaderNotNull(authenticationToken, "Authorization header Missing");

        //We check that the user trying to issue this required is registered in our database.
        //If the user is found to be registered in the database the userId is returned.
        //If it is not the case this method throws a ForbiddenException
        Long id = authorizationService.resourceAccessAuthorized(authenticationToken);

        List<UserDto> usersList;

        //we check that the user has access right on all data in the user tab
        if(userService.canUserAccessAllData(id)) {
            usersList = userService.getAllUsers();
        }
        else {
            usersList = new LinkedList<>();
            usersList.add(userService.getUserById(id));
        }

        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @GetMapping(value= "/{id}")
    public ResponseEntity<UserDto> getUser(@RequestHeader("Authorization") String authenticationToken, @PathVariable long id) {

        RestPreconditions.checkRequestHeaderNotNull(authenticationToken, "Authorization header Missing");

        //We check that the ID transmitted in authorization token is still valid
        // that is if the ID of the user trying to issue this request is registered in our database.
        //If the user is found to be registered in the database the userId stored in the jwt is returned.
        //If it is not the case this method throws a ForbiddenException
        Long userIdFromJWT = authorizationService.resourceAccessAuthorized(authenticationToken);



        if(! userService.canUserAccessAllData(userIdFromJWT)) {
            authorizationService.requestIdMatchesTokenId(userIdFromJWT, id);
        }

        UserDto user= userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody UserDto userDto) {

        userService.addUser(userDto);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

}
