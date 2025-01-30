package com.pointdafamilia.pointdafamilia.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pointdafamilia.pointdafamilia.dtos.ComidaDto;
import com.pointdafamilia.pointdafamilia.dtos.ComidaDtoAdmin;
import com.pointdafamilia.pointdafamilia.entities.Comida;
import com.pointdafamilia.pointdafamilia.services.ComidaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


@RestController
@RequestMapping("/api/comidas")
public class ComidasController {
    @Autowired
    private ComidaService comidaService;

    @PostMapping("/register-food")
    public ResponseEntity<Comida> createComida(@RequestBody @Valid ComidaDto novaComidaDto){
        Comida newComida = comidaService.createComida(novaComidaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newComida);
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<ComidaDto>> listAllComidas(){
        List<ComidaDto> comida = comidaService.listAllComidas();
        return ResponseEntity.ok(comida);
    }

    @GetMapping("/list-all-admin")
    public ResponseEntity<List<ComidaDtoAdmin>> listAllComidasAdmin(){
        List<ComidaDtoAdmin> comida = comidaService.listAllComidasAdmin();
        return ResponseEntity.ok(comida);
    }

    @GetMapping("/delete-{id}")
    public ResponseEntity<ComidaDto> getComidaById(@PathVariable @NotNull Long  id){
        ComidaDto comida = comidaService.getComidaById(id);
        return ResponseEntity.ok(comida);
    }

    @PutMapping("/update-{id}")
    public ResponseEntity<ComidaDto> updateComidaById(@PathVariable @NotNull Long id, @RequestBody @Valid ComidaDto comidaDto){
        ComidaDto comida = comidaService.updateComidaById(id, comidaDto);
        return ResponseEntity.ok(comida);
    }

    @DeleteMapping("/delete-{id}")
    public ResponseEntity<Void> deleteComidaById(@PathVariable Long id){
        comidaService.deleteComidaById(id);
        return ResponseEntity.noContent().build();
    }
}
