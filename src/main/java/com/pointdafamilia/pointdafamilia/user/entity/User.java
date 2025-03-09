package com.pointdafamilia.pointdafamilia.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @Override
    public String toString(){
        return "User with id: " + id + " name: " + name + " username: " + username
        + " email: " + email + " password: " + password + " imageUrl: " + imageUrl;
    }

}
