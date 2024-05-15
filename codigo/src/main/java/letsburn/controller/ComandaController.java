package letsburn.controller;

import letsburn.dto.ComandaDTO;
import letsburn.entidades.Comanda;
import letsburn.repository.ComandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/comanda")
public class ComandaController {

    @Autowired
    private ComandaRepository comandaRepository;

    @GetMapping
    public List<Comanda> listarComandas() {
        return comandaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comanda> buscarComanda(@PathVariable Integer id) {
        return comandaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Comanda> criarComanda(@RequestBody ComandaDTO comandaDTO) {
        Comanda novaComanda = new Comanda();
        novaComanda.setValorTotal(comandaDTO.valorTotal());
        // Lógica para adicionar itens à comanda pode ser adicionada aqui
        Comanda salva = comandaRepository.save(novaComanda);
        URI uri = URI.create(String.format("/comanda/%s", salva.getId()));
        return ResponseEntity.created(uri).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comanda> atualizarComanda(@PathVariable Integer id, @RequestBody ComandaDTO comandaDTO) {
        return comandaRepository.findById(id)
                .map(comandaExistente -> {
                    comandaExistente.setValorTotal(comandaDTO.valorTotal());
                    // Lógica para atualizar itens da comanda aqui
                    comandaRepository.save(comandaExistente);
                    return ResponseEntity.ok(comandaExistente);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarComanda(@PathVariable Integer id) {
        return comandaRepository.findById(id)
                .map(comanda -> {
                    comandaRepository.delete(comanda);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

//    @PostMapping("/{id}/adicionarItem/{itemId}")
//    public ResponseEntity<Comanda> adicionarItem(@PathVariable Integer id, @PathVariable Integer itemId) {
//        return comandaRepository.findById(id)
//                .map(comanda -> {
//                    itemService.adicionarItem(comanda, itemId);
//                    return ResponseEntity.ok(comanda);
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
}
