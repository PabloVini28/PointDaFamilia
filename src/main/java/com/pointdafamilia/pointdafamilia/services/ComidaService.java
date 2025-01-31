package com.pointdafamilia.pointdafamilia.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointdafamilia.pointdafamilia.dtos.ComidaDto;
import com.pointdafamilia.pointdafamilia.entities.Comida;
import com.pointdafamilia.pointdafamilia.exceptions.ComidaAlreadyRegisteredException;
import com.pointdafamilia.pointdafamilia.exceptions.ComidaNotFoundException;
import com.pointdafamilia.pointdafamilia.repository.ComidasRepository;

@Service
public class ComidaService {
    @Autowired
    private ComidasRepository comidasRepository;

    public Comida createComida(ComidaDto novaComidaDto){
        if(comidasRepository.existsByNome(novaComidaDto.nome())){
            throw new ComidaAlreadyRegisteredException(novaComidaDto.nome());
        }
        Comida newComida = new Comida(novaComidaDto);
        return comidasRepository.save(newComida);
    }

    public List<ComidaDto> listAllComidas(){
        List<Comida> comidas = comidasRepository.findAll();
        return comidas.stream().map(comida -> new ComidaDto(comida)).collect(Collectors.toList());
    }

    public ComidaDto updateComidaById(Long id, ComidaDto comidaDto){
        Comida comida = comidasRepository.findById(id).orElseThrow(()-> new ComidaNotFoundException(id));
        comida = new Comida(comidaDto);
        comida.setId(id);
        comidasRepository.save(comida);
        return comidaDto;
    }

    public ComidaDto getComidaById(Long id) {
        Comida activity = comidasRepository.findById(id).orElseThrow(() -> new ComidaNotFoundException(id));
        ComidaDto activityDto = new ComidaDto(activity);
        return activityDto;
    }

    public void deleteComidaById(Long id){
        if(!comidasRepository.existsById(id)){
            throw new ComidaNotFoundException(id);
        }else{
            comidasRepository.deleteById(id);
        }
    }
}
