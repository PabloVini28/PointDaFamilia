package com.pointdafamilia.pointdafamilia.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.pointdafamilia.pointdafamilia.DTOS.BebidasDTO;
import com.pointdafamilia.pointdafamilia.Services.BebidasService;
import lombok.RequiredArgsConstructor;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/bebidas")
@RequiredArgsConstructor
public class BebidasController {
    
    private final BebidasService bebidasService;

    @PostMapping("/register")
    public ResponseEntity<BebidasDTO> cadastrarBebidas(@RequestBody @Validated BebidasDTO bebidasDTO, UriComponentsBuilder uriBuilder) {
        BebidasDTO dto = bebidasService.criarBebida(bebidasDTO);
        URI address = uriBuilder.path("/bebida/{nome}").buildAndExpand(dto.getNome()).toUri();
        return ResponseEntity.created(address).body(dto);
    }
    

}
