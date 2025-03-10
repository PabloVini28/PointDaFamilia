package com.pointdafamilia.pointdafamilia.auth.dtos.request;

import com.pointdafamilia.pointdafamilia.user.enums.RoleType;

public record RegisterDto(
    String name,
    String username,
    String email,
    String password,
    String address,
    RoleType roleType
) {
    
}
