package com.pointdafamilia.pointdafamilia.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pointdafamilia.pointdafamilia.user.dtos.UserDto;
import com.pointdafamilia.pointdafamilia.user.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

    UserDto findItById(Long id);
    UserDto findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    UserDto findByEmail();
}
