package com.pointdafamilia.pointdafamilia.entities;

import com.pointdafamilia.pointdafamilia.enums.ComidasType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "comidas")
@Data
@RequiredArgsConstructor
public class Comida {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 50) @NotBlank
    private String nome;
    @Size(max = 1000) @NotBlank
    private String descricao;
    @NotBlank
    private String ingredientes;
    @NotBlank
    private String quantidade;
    @NotBlank
    private String valor;
    @NotBlank
    private String imagem;
    @Enumerated(EnumType.STRING)
    private ComidasType tipo;
}
