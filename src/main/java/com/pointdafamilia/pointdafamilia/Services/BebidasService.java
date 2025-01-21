package com.pointdafamilia.pointdafamilia.Services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.pointdafamilia.pointdafamilia.DTOS.BebidasDTO;
import com.pointdafamilia.pointdafamilia.Entities.Bebidas;
import com.pointdafamilia.pointdafamilia.Repositories.BebidasRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BebidasService {

    private final BebidasRepository bebidasRepository;
    private final ModelMapper modelMapper;

    public BebidasDTO criarBebida(BebidasDTO bebidasDTO) {
        Bebidas bebida = modelMapper.map(bebidasDTO, Bebidas.class);
        bebidasRepository.save(bebida);

        return modelMapper.map(bebida, BebidasDTO.class);
    }


}
