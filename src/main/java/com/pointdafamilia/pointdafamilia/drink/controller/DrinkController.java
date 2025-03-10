package com.pointdafamilia.pointdafamilia.drink.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pointdafamilia.pointdafamilia.drink.dtos.request.DrinkPatchImage;
import com.pointdafamilia.pointdafamilia.drink.dtos.request.DrinkPatchName;
import com.pointdafamilia.pointdafamilia.drink.dtos.request.DrinkPatchPrice;
import com.pointdafamilia.pointdafamilia.drink.dtos.request.DrinkPatchQuantity;
import com.pointdafamilia.pointdafamilia.drink.dtos.request.DrinkPatchType;
import com.pointdafamilia.pointdafamilia.drink.dtos.request.DrinkPatchVolume;
import com.pointdafamilia.pointdafamilia.drink.dtos.response.DrinkDto;
import com.pointdafamilia.pointdafamilia.drink.entity.Drink;
import com.pointdafamilia.pointdafamilia.drink.service.DrinkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/drink")
@RequiredArgsConstructor
public class DrinkController {
    
    @Autowired
    private DrinkService drinkService;

    @PostMapping("/create-drink")
    public ResponseEntity<Drink> createDrink(@RequestBody @Valid DrinkDto data) throws Exception {
        Drink newDrink = drinkService.createDrink(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDrink);
    }

    @GetMapping("/get-all-drink")
    public ResponseEntity<List<DrinkDto>> getAllDrink() {
        List<DrinkDto> drinkList = drinkService.getAllDrink();
        return ResponseEntity.ok(drinkList);
    }

    @GetMapping("/get-drink-by-id/{id}")
    public ResponseEntity<Drink> getDrinkById(@PathVariable Long id) throws Exception {
        Drink drink = drinkService.getDrinkById(id);
        return ResponseEntity.ok(drink);
    }
    
    @PutMapping("/update-drink-by-id/{id}")
    public ResponseEntity<DrinkDto> updateDrinkById(@PathVariable Long id, @RequestBody @Valid DrinkDto data) throws Exception {
        DrinkDto updatedDrink = drinkService.updateDrink(id, data);
        return ResponseEntity.ok(updatedDrink);
    }

    @DeleteMapping("/delete-drink-by-id/{id}")
    public ResponseEntity<Void> deleteDrinkById(@PathVariable Long id) throws Exception{
        drinkService.deleteDrink(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{name}/name")
    public ResponseEntity<DrinkDto> updateDrinkName(@RequestBody DrinkPatchName drinkPatchName, @PathVariable String name) throws Exception{
        DrinkDto drink = drinkService.updateName(drinkPatchName, name);
        return ResponseEntity.ok().body(drink);
    }

    @PatchMapping("/{name}/volume")
    public ResponseEntity<DrinkDto> updateDrinkVolume(@RequestBody DrinkPatchVolume drinkPatchVolume, @PathVariable String name) throws Exception{
        DrinkDto drink = drinkService.updateVolume(drinkPatchVolume, name);
        return ResponseEntity.ok().body(drink);
    }

    @PatchMapping("/{name}/type")
    public ResponseEntity<DrinkDto> updateDrinkType(@RequestBody DrinkPatchType drinkPatchType, @PathVariable String name) throws Exception{
        DrinkDto drink = drinkService.updateDrinkType(drinkPatchType, name);
        return ResponseEntity.ok().body(drink);
    }

    @PatchMapping("/{name}/price")
    public ResponseEntity<DrinkDto> updateDrinkPrice(@RequestBody DrinkPatchPrice drinkPatchPrice, @PathVariable String name) throws Exception{
        DrinkDto drink = drinkService.updatePrice(drinkPatchPrice, name);
        return ResponseEntity.ok().body(drink);
    }

    @PatchMapping("/{name}/quantity")
    public ResponseEntity<DrinkDto> updateDrinkQuantity(@RequestBody DrinkPatchQuantity drinkPatchQuantity, @PathVariable String name) throws Exception{
        DrinkDto drink = drinkService.updateQuantity(drinkPatchQuantity, name);
        return ResponseEntity.ok().body(drink);
    }

    @PatchMapping("/{name}/image")
    public ResponseEntity<DrinkDto> updateDrinkImage(@RequestBody DrinkPatchImage drinkPatchImage, @PathVariable String name) throws Exception{
        DrinkDto drink = drinkService.updateUrlImage(drinkPatchImage, name);
        return ResponseEntity.ok().body(drink);
    }
    
}
