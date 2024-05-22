package letsburn.Controller;

import letsburn.model.Mesa;
import letsburn.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mesas")
public class MesaController {

    @Autowired
    private MesaRepository mesaRepository;

    @PostMapping
    public ResponseEntity<Mesa> adicionarMesa(@RequestBody Mesa novaMesa) {
        Mesa mesa = new Mesa(novaMesa.getCapacidade());
        mesaRepository.save(mesa);
        return ResponseEntity.ok(mesa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> obterMesa(@PathVariable int id) {
        Optional<Mesa> mesa = mesaRepository.findById(id);
        return mesa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Mesa>> listarMesas() {
        List<Mesa> mesas = mesaRepository.findAll();
        return ResponseEntity.ok(mesas);
    }
}
