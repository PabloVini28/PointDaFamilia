package com.pointdafamilia.pointdafamilia.food.entity;

import java.util.List;
import com.pointdafamilia.pointdafamilia.food.dtos.FoodDto;
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
    private List<String> ingredients;

    @NotNull
    private Integer quantity;

    @NotNull
    private String price;

    @NotBlank
    private String urlImage;

    @Enumerated(EnumType.STRING)
    private FoodType type;

    public Food(FoodDto data) {
        this.name = data.name();
        this.description = data.description();
        this.ingredients = data.ingredients();
        this.quantity = data.quantity();
        this.price = data.price();
        this.urlImage = data.urlImage();
        this.type = data.type();
    }
    
}
