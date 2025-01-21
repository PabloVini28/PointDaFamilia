package com.pointdafamilia.pointdafamilia.DTOS;

import com.pointdafamilia.pointdafamilia.Entities.Tipo;

import lombok.Data;
import lombok.NonNull;

@Data
public class BebidasDTO {
    
    @NonNull
    private String nome;

    @NonNull
    private Tipo tipo;

    private float preco;

    @NonNull
    private String imagem;

}
