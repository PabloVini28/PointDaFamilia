package com.pointdafamilia.pointdafamilia.DTOS;

import com.pointdafamilia.pointdafamilia.Entities.Categoria;
import com.pointdafamilia.pointdafamilia.Entities.Tipo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BebidasDTOAdmin {
    
    private long id;

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
