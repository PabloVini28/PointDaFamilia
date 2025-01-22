package com.pointdafamilia.pointdafamilia.dtos;

import com.pointdafamilia.pointdafamilia.enums.Categoria;
import com.pointdafamilia.pointdafamilia.enums.Tipo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BebidasDTO {
    
    @NonNull
    private String nome;

    @NonNull
    private Tipo tipo;

    private float preco;

    @NonNull
    private Categoria categoria;
    
    @NonNull
    private String imagem;

}
