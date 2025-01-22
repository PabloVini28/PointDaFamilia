package com.pointdafamilia.pointdafamilia.entities;

import com.pointdafamilia.pointdafamilia.enums.BebidasCategoria;
import com.pointdafamilia.pointdafamilia.enums.BebidasTipo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bebidas")
public class Bebidas {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String nome;

    @Column (nullable = false) @Enumerated
    private BebidasTipo tipo;

    @Column(nullable = false) @Enumerated
    private BebidasCategoria categoria;

    @Column (nullable = false)
    private float preco;

    @Column (nullable = false)
    private String imagem;

    @Column (nullable = false)
    private int quantidade;


}
