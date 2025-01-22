package com.pointdafamilia.pointdafamilia.dtos;

import com.pointdafamilia.pointdafamilia.entities.Comida;
import com.pointdafamilia.pointdafamilia.enums.ComidasType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record ComidaDto(
    String nome,
    String descricao,
    String ingredientes,
    String valor,
    String imagem,
    @Enumerated(EnumType.STRING) ComidasType tipo
){
    public ComidaDto(Comida comida){
        this(comida.getNome(),
        comida.getDescricao(),
        comida.getIngredientes(),
        comida.getValor(),
        comida.getImagem(),
        comida.getTipo());
    }
}
