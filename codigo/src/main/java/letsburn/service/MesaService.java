package letsburn.service;

import letsburn.entidades.Mesa;
import letsburn.repository.MesaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MesaService {

    MesaRepository mesaRepository;

    public Optional<Mesa> buscarMesa(Long id) {
        return  mesaRepository.findById(id);
    }
}
