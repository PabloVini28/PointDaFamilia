package com.pointdafamilia.pointdafamilia.drink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pointdafamilia.pointdafamilia.drink.entity.Drink;

public interface DrinkRepository extends JpaRepository<Drink,Long>{

    boolean existsByName(String name);
    Drink findItById(Long id);
    Drink findByName(String name);
    
}
