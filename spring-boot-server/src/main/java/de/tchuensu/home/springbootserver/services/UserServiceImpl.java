package de.tchuensu.home.springbootserver.services;

import de.tchuensu.home.springbootserver.dao.dto.mapper.UserMapper;
import de.tchuensu.home.springbootserver.dao.dto.model.UserDto;
import de.tchuensu.home.springbootserver.dao.repository.UserRepository;
import de.tchuensu.home.springbootserver.model.User;
import de.tchuensu.home.springbootserver.util.RestPreconditions;
import de.tchuensu.home.springbootserver.web.exception.DuplicateEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User addUser(UserDto userDto) {

        // We check that the user we are trying to register is not using a username or a password which is already in use
        if( ( userRepository.findUserByUsername(userDto.getUsername()) ) != null) {
            throw new DuplicateEntityException("The username <<" + userDto.getUsername() + ">> is already in use.");
        }

        if( ( userRepository.findUserByEmail(userDto.getEmail()) ) != null) {
            throw new DuplicateEntityException("The e-mail <<" + userDto.getEmail() + ">> is already in use.");
        }

        User user = new User(userDto.getUsername(),
                             userDto.getEmail(),
                             bCryptPasswordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);
        return user;
    }

    @Override
    public UserDto getUserByUsername(String username) {

        User user = RestPreconditions.checkFound( userRepository.findUserByUsername(username), "The user with name " + username + " was not found");
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto getUserById(Long Id) {

        User user = RestPreconditions.checkFound(userRepository.findUserById(Id), "The targeted user was not found");
        return UserMapper.toUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> lisOfUsers = RestPreconditions.checkFound(userRepository.findAll(), "");
        List<UserDto> userDtoList = new LinkedList<>();
        for(User user : lisOfUsers) {
            userDtoList.add(UserMapper.toUserDto(user));
        }
        return userDtoList;
    }

    @Override
    public Long getUserId(String username) {

        User user = userRepository.findUserByUsername(username);
        if(user == null) {

            throw new NullPointerException("The user with username: " + username + " was not found.");
        }
        return user.getId();
    }

}
