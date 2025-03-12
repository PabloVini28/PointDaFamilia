package com.pointdafamilia.pointdafamilia.drink.entity;

import com.pointdafamilia.pointdafamilia.drink.dtos.response.DrinkDto;
import com.pointdafamilia.pointdafamilia.drink.enums.DrinkType;
import com.pointdafamilia.pointdafamilia.drink.enums.VolumeType;
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

@Table(name = "drink")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Drink {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255) @NotBlank
    private String name;

    @Enumerated(EnumType.STRING) 
    private VolumeType volumeType;

    @Enumerated(EnumType.STRING)
    private DrinkType drinkType;

    @NotNull
    private double price;

    @NotNull
    private Integer quantity;

    @NotBlank
    private String urlImage;

    public Drink(DrinkDto data) {
        this.name = data.name();
        this.volumeType = data.volumeType();
        this.drinkType = data.drinkType();
        this.price = data.price();
        this.quantity = data.quantity();
        this.urlImage = data.urlImage();
    }


}
