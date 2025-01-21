package com.pointdafamilia.pointdafamilia.Entities;

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
    private Tipo tipo;

    @Column(nullable = false) @Enumerated
    private Categoria categoria;

    @Column (nullable = false)
    private float preco;

    @Column (nullable = false)
    private String imagem;

    @Column (nullable = false)
    private int quantidade;


}
