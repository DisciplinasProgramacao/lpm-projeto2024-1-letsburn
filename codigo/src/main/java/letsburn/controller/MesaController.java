package letsburn.controller;

import letsburn.dto.RequestMesaDTO;
import letsburn.entidades.Mesa;
import letsburn.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/mesa")
public class MesaController {
    @Autowired
    private MesaRepository mesaRepository;


    @GetMapping
    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> buscarMesa(@PathVariable Integer id) {
        return mesaRepository.findById(id)
                .map(mesa -> ResponseEntity.ok().body(mesa))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mesa> criarMesa(@RequestBody RequestMesaDTO mesaDTO) {
        Mesa novaMesa = new Mesa();
        novaMesa.setCapacidade(mesaDTO.capacidade());
        novaMesa.setDisponivel(mesaDTO.disponivel());
        mesaRepository.save(novaMesa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novaMesa.getId()).toUri();

        return ResponseEntity.created(uri).body(novaMesa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mesa> atualizarMesa(@PathVariable Integer id, @RequestBody RequestMesaDTO mesaDTO) {
        return mesaRepository.findById(id)
                .map(mesa -> {
                    mesa.setCapacidade(mesaDTO.capacidade());
                    mesa.setDisponivel(mesaDTO.disponivel());
                    mesaRepository.save(mesa);
                    return ResponseEntity.ok().body(mesa);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarMesa(@PathVariable Integer id) {
        return mesaRepository.findById(id)
                .map(mesa -> {
                    mesaRepository.delete(mesa);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
