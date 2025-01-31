package com.pointdafamilia.pointdafamilia.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pointdafamilia.pointdafamilia.entities.Comida;


public interface ComidasRepository  extends JpaRepository<Comida, Long> {
    Boolean existsByNome(String ComidaDtoNome);
}
