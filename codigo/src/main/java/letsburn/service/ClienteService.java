package letsburn.service;

import letsburn.entidades.Cliente;
import letsburn.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClientRepository clientRepository;
    public Optional<Cliente> buscarCliente(Long id) {
        return clientRepository.findById(id);
    }
}
