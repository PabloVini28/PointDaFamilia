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
public class BebidasDTOAdmin {
    
    private long id;

    @NonNull
    private String nome;

    @NonNull
    private BebidasTipo tipo;

    @NonNull
    private String preco;

    @NonNull
    private BebidasCategoria categoria;

    @NonNull
    private String imagem;

}
