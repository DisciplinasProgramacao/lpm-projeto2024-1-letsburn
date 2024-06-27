package com.api.letsburn_restaurante.controller;

import com.api.letsburn_restaurante.dto.RequestAtenderClienteDTO;
import com.api.letsburn_restaurante.dto.ResponseComanda;
import com.api.letsburn_restaurante.service.RestauranteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @PostMapping("/atender-cliente")
    public ResponseEntity atenderCliente(@RequestBody RequestAtenderClienteDTO atenderClienteDTO) {
        Long idAtendimento = restauranteService.atenderCliente(atenderClienteDTO);
        return ResponseEntity.ok(idAtendimento);
    }

    @PutMapping("/fazer-pedido/{idRequisicao}")
    public void fazerPedido(@PathVariable Long idRequisicao, @RequestParam Long idItemCardapio) {
        restauranteService.fazerPedido(idRequisicao, idItemCardapio);
    }


    @PutMapping("/fechar-conta/{idRequisicao}")
    public ResponseEntity<ResponseComanda> fecharConta(@PathVariable Long idRequisicao) {
        ResponseComanda responseComanda = restauranteService.fecharConta(idRequisicao);
        return ResponseEntity.ok(responseComanda);
    }
}
