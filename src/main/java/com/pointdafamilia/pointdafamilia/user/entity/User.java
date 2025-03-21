package com.pointdafamilia.pointdafamilia.user.entity;

import com.pointdafamilia.pointdafamilia.auth.dtos.request.RegisterDto;
import com.pointdafamilia.pointdafamilia.user.enums.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank 
    @Size(min = 3)
    private String name;

    @NotBlank @Size(min = 4) 
    @Column(unique = true)
    @Pattern(regexp = "^[A-Za-z0-9+/=]*$", message = "Username with letters and numbers only")
    private String username;

    @Email 
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    private String imageUrl;

    @NotBlank @Size(max = 35)
    private String address;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public User(RegisterDto data, String password, RoleType roleType){
        this.name = data.name();
        this.username = data.username();
        this.email = data.email();
        this.password = password;
        this.address = data.address();
        this.roleType = roleType;
    }

    @Override
    public String toString(){
        return "User with id: " + id + " name: " + name + " username: " + username
        + " email: " + email + " password: " + password + " imageUrl: " + imageUrl
        + " address: "+ address + " Role: " + roleType;
    }

}
