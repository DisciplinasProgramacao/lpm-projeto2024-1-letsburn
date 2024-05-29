package letsburn.service;

import letsburn.entidades.Mesa;
import letsburn.repository.MesaRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MesaService {

    MesaRepository mesaRepository;

    public Optional<Mesa> buscarMesa(Long id) {
        return  mesaRepository.findById(id);
    }

    public Mesa criarMesa(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    private List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    public Optional<Mesa> buscarMelhorMesaDisponivel(int qtdPessoas) {
        List<Mesa> mesas = listarMesas();
        return mesas.stream().filter(m -> m.getCapacidade() >= qtdPessoas && !m.isOcupada()).min(Comparator.comparingInt(Mesa::getCapacidade));
    }
}
