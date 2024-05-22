package letsburn.controller;

import letsburn.entidades.Comanda;
import letsburn.entidades.ItemCardapio;
import letsburn.service.ComandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comanda")
public class ComandaController {

    @Autowired
    private ComandaService comandaService;

    @PostMapping
    public Comanda criarComanda(@RequestBody Comanda comanda) {
        return comandaService.criarComanda(comanda);
    }

    @GetMapping
    public List<Comanda> listarComandas() {
        return comandaService.listarComandas();
    }

    @GetMapping("/{id}")
    public Optional<Comanda> buscarComanda(@PathVariable Long id) {
        return comandaService.buscarComanda(id);
    }

    @PostMapping("/adicionar-item/{id}")
    public Comanda adicionarItem(@PathVariable Long id, @RequestBody ItemCardapio item) {
        return comandaService.adicionarPedido(id, item);
    }

    @PostMapping("/remover-item/{id}")
    public Comanda removerItem(@PathVariable Long id, @RequestBody ItemCardapio item) {
        return comandaService.removerPedido(id, item);
    }
}
