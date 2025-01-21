package com.pointdafamilia.pointdafamilia.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pointdafamilia.pointdafamilia.Entities.Bebidas;

import jakarta.persistence.Id;

public interface BebidasRepository extends JpaRepository<Long,Id>{

    void save(Bebidas bebida);
    
}
