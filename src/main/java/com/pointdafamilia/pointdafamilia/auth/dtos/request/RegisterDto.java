package com.pointdafamilia.pointdafamilia.auth.dtos.request;

public record RegisterDto(
    String name,
    String username,
    String email,
    String password,
    String endereco
) {
    
}
