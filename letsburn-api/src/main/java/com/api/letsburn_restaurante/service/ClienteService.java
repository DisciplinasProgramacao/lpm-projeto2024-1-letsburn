package com.api.letsburn_restaurante.service;

import com.api.letsburn_restaurante.model.Cliente;
import com.api.letsburn_restaurante.repository.ClientRepository;
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

    public Cliente cadastrarCliente(Cliente cliente) {
        return clientRepository.save(cliente);
    }
}
