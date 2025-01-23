package com.pointdafamilia.pointdafamilia.dtos;

import java.util.Vector;

import com.pointdafamilia.pointdafamilia.entities.Comida;
import com.pointdafamilia.pointdafamilia.enums.ComidasType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record ComidaDto(
    String nome,
    String descricao,
    Vector<String> ingredientes,
    Float valor,
    String imagem,
    Integer quantidade,
    @Enumerated(EnumType.STRING) ComidasType tipo
){
    public ComidaDto(Comida comida){
        this(comida.getNome(),
        comida.getDescricao(),
        comida.getIngredientes(),
        comida.getValor(),
        comida.getImagem(),
        comida.getQuantidade(),
        comida.getTipo());
    }
}
