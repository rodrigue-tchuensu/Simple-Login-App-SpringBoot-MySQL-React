package de.tchuensu.home.springbootserver.services;

import de.tchuensu.home.springbootserver.model.User;

import java.util.List;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

public interface UserService {

    /**
     *
     *
     * @param userDto
     * @return
     */
    //User registerUser(UserDto userDto);

    /**
     *
     *
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     *
     *
     * @param Id
     * @return
     */
    User findUserById(Long Id);

    /**
     *
     * @return
     */
    List<User> findAllUsers();


    //void changePassword(User user, String newPassword);
}
