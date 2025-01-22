package com.pointdafamilia.pointdafamilia.dtos;

import com.pointdafamilia.pointdafamilia.enums.BebidasCategoria;
import com.pointdafamilia.pointdafamilia.enums.BebidasTipo;

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
    private BebidasTipo tipo;

    private float preco;

    @NonNull
    private BebidasCategoria categoria;
    
    @NonNull
    private String imagem;

}
