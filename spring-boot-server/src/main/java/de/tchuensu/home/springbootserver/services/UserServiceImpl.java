package de.tchuensu.home.springbootserver.services;

import de.tchuensu.home.springbootserver.dao.dto.mapper.UserMapper;
import de.tchuensu.home.springbootserver.dao.dto.model.UserDto;
import de.tchuensu.home.springbootserver.dao.repository.UserDao;
import de.tchuensu.home.springbootserver.model.User;
import de.tchuensu.home.springbootserver.util.RestPreconditions;
import de.tchuensu.home.springbootserver.web.exception.DuplicateEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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
    public User addUser(UserDto userDto) {

        // We check that the user we are trying to register is not using a username or a password which is already in use
        if( ( userDao.findUserByUsername(userDto.getUsername()) ) != null) {
            throw new DuplicateEntityException("The username <<" + userDto.getUsername() + ">> is already in use.");
        }

        if( ( userDao.findUserByEmail(userDto.getEmail()) ) != null) {
            throw new DuplicateEntityException("The e-mail <<" + userDto.getEmail() + ">> is already in use.");
        }

        User user = new User(userDto.getUsername(),
                             userDto.getEmail(),
                             passwordEncryptorManager.generatePasswordHash(userDto.getPassword()));

        userDao.save(user);
        return user;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = RestPreconditions.checkFound( userDao.findUserByUsername(username), "The user with name " + username + " was not found");
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto getUserById(Long Id) {
        User user = RestPreconditions.checkFound(userDao.findUserById(Id), "The targeted user was not found");
        return UserMapper.toUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> lisOfUsers = RestPreconditions.checkFound(userDao.findAll(), "");
        List<UserDto> userDtoList = new LinkedList<>();
        for(User user : lisOfUsers) {
            userDtoList.add(UserMapper.toUserDto(user));
        }
        return userDtoList;
    }


    // Utility Methods

    public boolean canUserAccessAllData(Long id) {
        return (userDao.findUserById(id)).isCanAccessAllData();
    }
}
