package de.tchuensu.home.springbootserver.security;

import de.tchuensu.home.springbootserver.dao.dto.model.UserDto;
import de.tchuensu.home.springbootserver.services.UserServiceImpl;
import de.tchuensu.home.springbootserver.web.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto user;
        try {
            user = userService.getUserByUsername(username);
        } catch(ResourceNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),emptyList());
    }
}
