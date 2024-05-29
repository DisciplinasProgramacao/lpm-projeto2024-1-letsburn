package com.api.letsburn_restaurante.service;

import com.api.letsburn_restaurante.model.Mesa;
import com.api.letsburn_restaurante.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public Optional<Mesa> buscarMesa(Long id) {
        return  mesaRepository.findById(id);
    }

    public Mesa criarMesa(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public long contarMesas() {
        return mesaRepository.count();
    }
    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    public Optional<Mesa> buscarMelhorMesaDisponivel(int qtdPessoas) {
        List<Mesa> mesas = listarMesas();
        return mesas.stream().filter(m -> m.getCapacidade() >= qtdPessoas && !m.isOcupada()).min(Comparator.comparingInt(Mesa::getCapacidade));
    }


}
