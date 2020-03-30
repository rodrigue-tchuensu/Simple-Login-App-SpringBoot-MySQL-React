package de.tchuensu.home.springbootserver.services;

import de.tchuensu.home.springbootserver.dao.UserDao;
import de.tchuensu.home.springbootserver.model.User;
import de.tchuensu.home.springbootserver.util.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncryptorManager passwordEncryptorManager;

    @Override
    public User findUserByUsername(String username) {
        User user = RestPreconditions.checkFound( userDao.findUserByUsername(username), "The user with name " + username + " was not found");
        return user;
    }

    @Override
    public User findUserById(Long Id) {
        User user = RestPreconditions.checkFound(userDao.findUserById(Id), "The targeted user was not found");
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> lisOfUsers = RestPreconditions.checkFound(userDao.findAll(), "");
        return lisOfUsers;
    }
}
