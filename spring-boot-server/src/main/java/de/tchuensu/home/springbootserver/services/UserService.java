package de.tchuensu.home.springbootserver.services;

import de.tchuensu.home.springbootserver.dao.dto.model.UserDto;
import de.tchuensu.home.springbootserver.model.User;

import java.util.List;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

public interface UserService {


    // Create

    /**
     *
     *  Saves a new user
     *
     * @param userDto
     * @return
     */
    UserDto create(UserDto userDto);
    //void changePassword(User user, String newPassword);


    // Read
    /**
     *
     *  Gets a user with the corresponding username
     *
     * @param username
     * @return
     */
    UserDto getByUsername(String username);

    /**
     *
     *  Gets a user eith the coressponding Id number
     *
     * @param Id
     * @return
     */
    UserDto getUserById(Long Id);

    /**
     *
     *  Gets all the users stored in the db
     *
     * @return
     */
    List<UserDto> getUsers();


    // Update


    // Delete


}
