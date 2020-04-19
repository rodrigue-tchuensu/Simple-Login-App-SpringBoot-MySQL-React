package de.tchuensu.home.springbootserver.dao.dto.mapper;

import de.tchuensu.home.springbootserver.dao.dto.model.UserDetails;
import de.tchuensu.home.springbootserver.dao.dto.model.UserDto;
import de.tchuensu.home.springbootserver.model.User;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto( user.getUsername(),
                            user.getEmail(),
                            user.getPasswordHash()
        );
    }

    public static UserDetails toUserDetails(User user) {
        return  new UserDetails( user.getUsername(),
                user.getEmail());
    }

}
