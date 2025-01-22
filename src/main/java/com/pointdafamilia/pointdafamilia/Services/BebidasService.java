package com.pointdafamilia.pointdafamilia.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pointdafamilia.pointdafamilia.dtos.BebidasDTO;
import com.pointdafamilia.pointdafamilia.dtos.BebidasDTOAdmin;
import com.pointdafamilia.pointdafamilia.entities.Bebidas;
import com.pointdafamilia.pointdafamilia.exceptions.BebidaNotFound;
import com.pointdafamilia.pointdafamilia.repositories.BebidasRepository;

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
        Bebidas bebida = bebidasRepository.findById(id).orElseThrow(()-> new BebidaNotFound(id));
        return modelMapper.map(bebida, BebidasDTO.class);
    }

    public BebidasDTO atualizarBebidas(Long id, BebidasDTO bebidasDTO) {
        Bebidas bebida = bebidasRepository.findById(id).orElseThrow(()-> new BebidaNotFound(id));
        bebida.setNome(bebidasDTO.getNome());
        bebida.setPreco(bebidasDTO.getPreco());
        bebida.setTipo(bebidasDTO.getTipo());
        bebida.setImagem(bebidasDTO.getImagem());
        bebida.setCategoria(bebidasDTO.getCategoria());
        bebidasRepository.save(bebida);

        return modelMapper.map(bebida, BebidasDTO.class);
    }

    public void deletarBebida(Long id) {
        if (!bebidasRepository.existsById(id)) {
            throw new BebidaNotFound(id);
        }
        bebidasRepository.deleteById(id);
    }

    public Page<BebidasDTOAdmin> buscarPorTodasAsBebidasAdmin(Pageable page) {
        return bebidasRepository.findAll(page).map(t -> modelMapper.map(t,BebidasDTOAdmin.class));
    }




}
