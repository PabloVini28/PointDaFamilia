package com.pointdafamilia.pointdafamilia.DTOS;

import com.pointdafamilia.pointdafamilia.Entities.Tipo;

import lombok.NonNull;

public class BebidasDTO {
    
    @NonNull
    private String nome;

    @NonNull
    private Tipo tipo;

    @SuppressWarnings("unused")
    private float preco;

    @NonNull
    private String imagem;

}
