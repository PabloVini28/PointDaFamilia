package com.pointdafamilia.pointdafamilia.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.pointdafamilia.pointdafamilia.Entities.Bebidas;

import jakarta.persistence.Id;

public interface BebidasRepository extends CrudRepository<Long,Id>{

    void save(Bebidas bebida);
    
}
