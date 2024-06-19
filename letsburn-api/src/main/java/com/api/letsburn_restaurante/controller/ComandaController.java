package com.api.letsburn_restaurante.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.letsburn_restaurante.model.Comanda;
import com.api.letsburn_restaurante.model.ItemCardapio;
import com.api.letsburn_restaurante.repository.ComandaRepository;
import com.api.letsburn_restaurante.repository.ItemRepository;

@RestController
@RequestMapping("/comandas")
public class ComandaController {

    @Autowired
    private ComandaRepository comandaRepository;

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping
    public ResponseEntity<Comanda> criarComanda(@RequestBody Comanda comanda) {
        Comanda novaComanda = comandaRepository.save(comanda);
        return ResponseEntity.ok(novaComanda);
    }

    @PostMapping("/{id}/adicionar")
    public ResponseEntity<Comanda> adicionarProduto(@PathVariable Long id, @RequestBody String itemNome) {
        Optional<Comanda> comandaOptional = comandaRepository.findById(id);
        if (comandaOptional.isPresent()) {
            Comanda comanda = comandaOptional.get();
            Optional<ItemCardapio> itemOptional = itemRepository.findByNome(itemNome);
            if (itemOptional.isPresent()) {
                comanda.adicionarPedido(itemOptional.get());
                comandaRepository.save(comanda);
                return ResponseEntity.ok(comanda);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/fechar")
    public ResponseEntity<String> fecharComanda(@PathVariable Long id) {
        Optional<Comanda> comandaOptional = comandaRepository.findById(id);
        if (comandaOptional.isPresent()) {
            // Lógica para fechar a comanda
            return ResponseEntity.ok("Comanda fechada com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/preco")
    public ResponseEntity<Double> perguntarPreco(@PathVariable Long id) {
        Optional<Comanda> comandaOptional = comandaRepository.findById(id);
        if (comandaOptional.isPresent()) {
            Comanda comanda = comandaOptional.get();
            double precoTotal = comanda.getPedidos().stream().mapToDouble(ItemCardapio::getPreco).sum();
            return ResponseEntity.ok(precoTotal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
