package com.pointdafamilia.pointdafamilia.Services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.pointdafamilia.pointdafamilia.DTOS.BebidasDTO;
import com.pointdafamilia.pointdafamilia.DTOS.BebidasDTOAdmin;
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

    public Page<BebidasDTO> buscarTodasAsBebidas(Pageable page) {
        return bebidasRepository.findAll(page).map(t -> modelMapper.map(t,BebidasDTO.class));
    }

    public BebidasDTO buscarPorId(Long id) {
        Bebidas bebida = bebidasRepository.findById(id).orElseThrow();
        return modelMapper.map(bebida, BebidasDTO.class);
    }

    public BebidasDTO atualizarBebidas(Long id, BebidasDTO bebidasDTO) {
        Bebidas bebida = modelMapper.map(bebidasDTO, Bebidas.class);
        bebida.setId(id);
        bebida = bebidasRepository.save(bebida);
        return modelMapper.map(bebida, BebidasDTO.class);
    }

    public void deletarBebida(Long id) {
        bebidasRepository.deleteById(id);
    }

    public Page<BebidasDTOAdmin> buscarPorTodasAsBebidasAdmin(Pageable page) {
        return bebidasRepository.findAll(page).map(t -> modelMapper.map(t,BebidasDTOAdmin.class));
    }




}
