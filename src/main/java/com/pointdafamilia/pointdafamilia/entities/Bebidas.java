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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bebidas")
public class Bebidas {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false) @Size(max = 50) @NotBlank
    private String nome;

    @Column (nullable = false) @Enumerated
    private BebidasTipo tipo;

    @Column(nullable = false) @Enumerated
    private BebidasCategoria categoria;

    @Column (nullable = false) @NotBlank
    private String preco;

    @Column (nullable = false) @NotBlank
    private String imagem;

    @Column (nullable = false) @NotNull
    private int quantidade;


}
