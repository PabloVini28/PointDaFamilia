package com.pointdafamilia.pointdafamilia.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pointdafamilia.pointdafamilia.user.dtos.response.UserDto;
import com.pointdafamilia.pointdafamilia.user.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByName(String name);
    boolean existsByImageUrl(String image);

    User findByUsername(String username);
    User findByEmail(String email);
    User findByName(String name);
    User findByImageUrl(String image);

    UserDto findItById(Long id);
    boolean existsByAddress(String address);
    
}
