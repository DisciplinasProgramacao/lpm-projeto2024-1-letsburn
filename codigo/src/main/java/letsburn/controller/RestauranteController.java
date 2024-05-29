package letsburn.controller;

import letsburn.dto.RequestAtenderClienteDTO;
import letsburn.dto.ResponseComanda;
import letsburn.service.RestauranteService;
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

    @PutMapping("/fazer-pedido/{id}")
    public void fazerPedido(@PathVariable Long id, @RequestParam Long idItemCardapio) {
        restauranteService.fazerPedido(id, idItemCardapio);
    }

    @PutMapping("/fechar-conta/{id}")
    public ResponseEntity<ResponseComanda> fecharConta(@PathVariable Long id) {
        ResponseComanda responseComanda = restauranteService.fecharConta(id);
        return ResponseEntity.ok(responseComanda);
    }
}
