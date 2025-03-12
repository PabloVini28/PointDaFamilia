package com.pointdafamilia.pointdafamilia.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.pointdafamilia.pointdafamilia.fileStorage.entity.FilePrefix;
import com.pointdafamilia.pointdafamilia.fileStorage.service.FileStorageService;
import com.pointdafamilia.pointdafamilia.user.dtos.request.UserPatchAddress;
import com.pointdafamilia.pointdafamilia.user.dtos.request.UserPatchImage;
import com.pointdafamilia.pointdafamilia.user.dtos.request.UserPatchName;
import com.pointdafamilia.pointdafamilia.user.dtos.request.UserPatchUsername;
import com.pointdafamilia.pointdafamilia.user.dtos.response.UserDto;
import com.pointdafamilia.pointdafamilia.user.entity.User;
import com.pointdafamilia.pointdafamilia.user.exceptions.UserNameNotFoundException;
import com.pointdafamilia.pointdafamilia.user.exceptions.UsernamePatchAlreadyExists;
import com.pointdafamilia.pointdafamilia.user.exceptions.NameNotFoundException;
import com.pointdafamilia.pointdafamilia.user.exceptions.NamePatchAlreadyExists;
import com.pointdafamilia.pointdafamilia.user.exceptions.UserEmailNotFoundException;
import com.pointdafamilia.pointdafamilia.user.exceptions.UserIdNotFoundException;
import com.pointdafamilia.pointdafamilia.user.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileStorageService fileStorageService;

    public UserDto getById(Long id) throws Exception{
        if(!userRepository.existsById(id)){
            throw new UserIdNotFoundException(id);
        }
        UserDto user = userRepository.findItById(id);
        return user;
    }

    public User getByUsername(String username) throws Exception{
        if(!userRepository.existsByUsername(username)){
            throw new UserNameNotFoundException(username);
        }
        User user = userRepository.findByUsername(username);
        return user;
    }

    public User getByEmail(String email) throws Exception{
        if(!userRepository.existsByEmail(email)){
            throw new UserEmailNotFoundException(email);
        }
        User user = userRepository.findByEmail(email);
        return user;
    }

    public UserDto updateImage(UserPatchImage userPatchImage, String username) throws Exception{
    
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        
        FilePrefix prefix = new FilePrefix("user_image");
        fileStorageService.persistImage(prefix, userPatchImage.imageTempPath());

        if(user.getImageUrl() != null && fileStorageService.fileExists(user.getImageUrl())){
            fileStorageService.deleteFile(user.getImageUrl());
        }
        user.setImageUrl(prefix.getDisplayName()+"/"+userPatchImage.imageTempPath());
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto updateName(UserPatchName userPatchName, String name) throws Exception{
        if(!userRepository.existsByName(name)){
            throw new NameNotFoundException(name);
        }

        if(userRepository.existsByName(userPatchName.name())){
            throw new NamePatchAlreadyExists(userPatchName.name());
        }

        User updatedName = userRepository.findByName(name);
        updatedName.setName(userPatchName.name());
        userRepository.save(updatedName);
        return modelMapper.map(updatedName, UserDto.class);
    }
    
    public UserDto updateUsername(UserPatchUsername userPatchUsername, String username) throws Exception{
        if(!userRepository.existsByUsername(username)){
            throw new UserNameNotFoundException(username);
        }

        if(userRepository.existsByUsername(userPatchUsername.username())){
            throw new UsernamePatchAlreadyExists(userPatchUsername.username());
        }

        User updatedUsername = userRepository.findByUsername(username);
        updatedUsername.setUsername(userPatchUsername.username());
        userRepository.save(updatedUsername);
        return modelMapper.map(updatedUsername, UserDto.class);
    }

    public UserDto updateAddress(UserPatchAddress userPatchAddress, String username) throws Exception{
        if(!userRepository.existsByUsername(username)){
            throw new UserNameNotFoundException(username);
        }

        User updatedAddress = userRepository.findByUsername(username);
        updatedAddress.setAddress(userPatchAddress.address());
        userRepository.save(updatedAddress);
        return modelMapper.map(updatedAddress, UserDto.class);
    }

}
