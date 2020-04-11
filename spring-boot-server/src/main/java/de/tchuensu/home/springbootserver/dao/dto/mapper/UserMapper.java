package de.tchuensu.home.springbootserver.dao.dto.mapper;

import de.tchuensu.home.springbootserver.dao.dto.model.UserDto;
import de.tchuensu.home.springbootserver.model.User;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto( user.getUsername(),
                            user.getEmail(),
                            user.getPasswordHash()
        );
    }

    public static User fromUserDto(UserDto userDto) {
        return null;
    }
}
