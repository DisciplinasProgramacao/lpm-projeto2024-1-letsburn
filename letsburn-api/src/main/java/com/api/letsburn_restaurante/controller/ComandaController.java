package com.api.letsburn_restaurante.controller;

import com.api.letsburn_restaurante.model.Comanda;
import com.api.letsburn_restaurante.service.ComandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comandas")
public class ComandaController {

    @Autowired
    private ComandaService comandaService;

    @PostMapping
    public ResponseEntity<Comanda> criarComanda(@RequestBody Comanda comanda) {
        Comanda novaComanda = comandaService.criarComanda(comanda);
        return ResponseEntity.ok(novaComanda);
    }

    @GetMapping
    public ResponseEntity<List<Comanda>> listarComandas() {
        List<Comanda> comandas = comandaService.listarComandas();
        return ResponseEntity.ok(comandas);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Comanda> buscarComanda(@PathVariable Long id) {
        Comanda comanda = comandaService.buscarComanda(id);
        return ResponseEntity.ok(comanda);
    }

    @PutMapping("/{id}/adicionar-pedido")
    public ResponseEntity<Comanda> adicionarPedido(@PathVariable Long id, @RequestParam Long idItemCardapio) {
        Comanda comanda = comandaService.adicionarPedido(id, idItemCardapio);
        return ResponseEntity.ok(comanda);
    }

    @PutMapping("/{id}/remover-pedido")
    public ResponseEntity<Comanda> removerPedido(@PathVariable Long id, @RequestParam Long idItemCardapio) {
        Comanda comanda = comandaService.removerPedido(id, idItemCardapio);
        return ResponseEntity.ok(comanda);
    }
}
