package com.pointdafamilia.pointdafamilia.user.dtos;

import com.pointdafamilia.pointdafamilia.user.entity.User;

public record UserDto(
    String name,
    String username,
    String email,
    String password,
    String imageUrl
) {
    UserDto(User user){
        this(user.getName(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            user.getImageUrl()
        );
    }
}
