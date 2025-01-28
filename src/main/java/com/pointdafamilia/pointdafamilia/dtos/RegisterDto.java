package com.pointdafamilia.pointdafamilia.dtos;
import com.pointdafamilia.pointdafamilia.enums.Role;

public record RegisterDto(String login, String password,Role role) {
} 
