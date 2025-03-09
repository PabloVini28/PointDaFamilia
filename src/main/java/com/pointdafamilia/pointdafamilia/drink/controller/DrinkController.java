package com.pointdafamilia.pointdafamilia.drink.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pointdafamilia.pointdafamilia.drink.dtos.DrinkDto;
import com.pointdafamilia.pointdafamilia.drink.entity.Drink;
import com.pointdafamilia.pointdafamilia.drink.service.DrinkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/drink")
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
    public ResponseEntity<Void> deleteDrinkById(Long id) throws Exception{
        drinkService.deleteDrink(id);
        return ResponseEntity.noContent().build();
    }
    
}
