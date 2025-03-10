package com.pointdafamilia.pointdafamilia.user.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pointdafamilia.pointdafamilia.user.dtos.request.UserPatchImage;
import com.pointdafamilia.pointdafamilia.user.dtos.request.UserPatchName;
import com.pointdafamilia.pointdafamilia.user.dtos.request.UserPatchUsername;
import com.pointdafamilia.pointdafamilia.user.dtos.response.UserDto;
import com.pointdafamilia.pointdafamilia.user.entity.User;
import com.pointdafamilia.pointdafamilia.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/get-user-by-id/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) throws Exception {
        UserDto user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get-user-by-username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) throws Exception {
        User user = userService.getByUsername(username);
        return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
    }
    
    @GetMapping("/get-user-by-email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) throws Exception {
        User user = userService.getByEmail(email);
        return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
    }

    @PatchMapping("/{username}/username")
    public ResponseEntity<UserDto> updateUsername(@RequestBody UserPatchUsername userPatchUsername, @PathVariable String username) throws Exception{
        UserDto user = userService.updateUsername(userPatchUsername, username);
        return ResponseEntity.ok().body(user);
    }
    
    @PatchMapping("/{username}/name")
    public ResponseEntity<UserDto> updateName(@RequestBody UserPatchName userPatchName, @PathVariable String username) throws Exception{
        UserDto user = userService.updateName(userPatchName, username);
        return ResponseEntity.ok().body(user);
    }

    @PatchMapping("/{username}/image")
    public ResponseEntity<UserDto> updateImage(@RequestBody UserPatchImage userPatchImage, @PathVariable String username) throws Exception{
        UserDto user = userService.updateImage(userPatchImage, username);
        return ResponseEntity.ok().body(user);
    }

}
