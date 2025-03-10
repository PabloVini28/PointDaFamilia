package com.pointdafamilia.pointdafamilia.user.dtos.response;

import com.pointdafamilia.pointdafamilia.user.entity.User;
import com.pointdafamilia.pointdafamilia.user.enums.RoleType;

public record UserDto(
    String name,
    String username,
    String email,
    String imageUrl,
    RoleType roleType
) {
   UserDto(User user){
        this(user.getName(),
            user.getUsername(),
            user.getEmail(),
            user.getImageUrl(),
            user.getRoleType()
            );
   }
}
