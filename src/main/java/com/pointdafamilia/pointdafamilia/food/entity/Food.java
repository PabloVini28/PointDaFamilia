package com.pointdafamilia.pointdafamilia.food.entity;

import java.util.Map;

import com.pointdafamilia.pointdafamilia.food.enums.FoodType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "food")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Food {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255) @NotBlank
    private String name;

    @Size(max = 1000) @NotBlank
    private String description;

    @NotNull
    private Map<String, String> ingredients;

    @NotNull
    private Integer quantity;

    @NotNull
    private Float price;

    @NotBlank
    private String UrlImage;

    @Enumerated(EnumType.STRING)
    private FoodType type;
    
}
