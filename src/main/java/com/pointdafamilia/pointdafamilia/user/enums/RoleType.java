package com.pointdafamilia.pointdafamilia.user.enums;

public enum RoleType {
    ROLE_ADMIN("admin"),
    ROLE_COMMON("common");

    private final String role;

    RoleType(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
