package de.tchuensu.home.springbootserver.web.api;


import de.tchuensu.home.springbootserver.dao.dto.model.UserDetails;
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

        List<UserDto> usersList = userService.getAllUsers();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @GetMapping(value= "/{username}")
    public ResponseEntity<UserDetails> getUser(@RequestHeader("Authorization") String authenticationToken, @PathVariable String username) {

        UserDto user= userService.getUserByUsername(username);
        UserDetails userDetails = new UserDetails(user.getUsername(), user.getEmail());
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody UserDto userDto) {

        userService.addUser(userDto);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

}
