package com.pointdafamilia.pointdafamilia.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.Id;

public interface BebidasRepository extends JpaRepository<Long,Id>{
    
}
