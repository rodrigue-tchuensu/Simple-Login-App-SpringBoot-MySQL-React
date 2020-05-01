package de.tchuensu.home.springbootserver.web.api;


import de.tchuensu.home.springbootserver.dao.dto.model.UserDto;
import de.tchuensu.home.springbootserver.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestHeader("Authorization") String authenticationToken) {

        List<UserDto> usersList = userService.getUsers();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @GetMapping(value= "/{username}")
    public ResponseEntity<UserDto> getUser(@RequestHeader("Authorization") String authenticationToken, @PathVariable String username) {

        UserDto user= userService.getByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {

        UserDto user = userService.create(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}