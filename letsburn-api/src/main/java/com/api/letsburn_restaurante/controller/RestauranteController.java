package com.api.letsburn_restaurante.controller;

import com.api.letsburn_restaurante.dto.RequestAtenderClienteDTO;
import com.api.letsburn_restaurante.dto.RequestPedido;
import com.api.letsburn_restaurante.dto.ResponseComanda;
import com.api.letsburn_restaurante.model.Comanda;
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
        Long idAtendimento =  restauranteService.atenderCliente(atenderClienteDTO);
        return ResponseEntity.ok(idAtendimento);
    }

    @PutMapping("/fazer-pedido")
    public ResponseEntity<Comanda> fazerPedido(@RequestBody RequestPedido requestPedido) {
        return ResponseEntity.ok(restauranteService.fazerPedido(requestPedido));
    }

    @PutMapping("/fechar-conta/{id}")
    public ResponseEntity<ResponseComanda> fecharConta(@PathVariable Long id) {
        ResponseComanda responseComanda = restauranteService.fecharConta(id);
        return ResponseEntity.ok(responseComanda);
    }
}
