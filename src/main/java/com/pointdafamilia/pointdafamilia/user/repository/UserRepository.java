package com.pointdafamilia.pointdafamilia.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pointdafamilia.pointdafamilia.user.dtos.UserDto;
import com.pointdafamilia.pointdafamilia.user.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByName(String name);
    boolean existsByImageUrl(String image);

    User findByUsername(String username);
    User findByEmail();
    User findByName(String name);
    User findByImageUrl(String image);

    UserDto findItById(Long id);
    
}
