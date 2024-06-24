package com.api.letsburn_restaurante.service;

import com.api.letsburn_restaurante.exception.ResourceNotFoundException;
import com.api.letsburn_restaurante.model.Comanda;
import com.api.letsburn_restaurante.model.ItemCardapio;
import com.api.letsburn_restaurante.repository.ComandaRepository;
import com.api.letsburn_restaurante.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComandaService {

    @Autowired
    private ComandaRepository comandaRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Comanda criarComanda(Comanda comanda) {
        return comandaRepository.save(comanda);
    }

    public List<Comanda> listarComandas() {
        return comandaRepository.findAll();
    }

    public Comanda buscarComanda(Long id) {
        return comandaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comanda não encontrada com id " + id));
    }

    public Comanda adicionarPedido(Long id, Long idItemCardapio) {
        Comanda comanda = buscarComanda(id);
        ItemCardapio item = itemRepository.findById(idItemCardapio)
                .orElseThrow(() -> new ResourceNotFoundException("ItemCardapio não encontrado com id " + idItemCardapio));
        comanda.adicionarPedido(item);
        return comandaRepository.save(comanda);
    }

    public Comanda removerPedido(Long id, Long idItemCardapio) {
        Comanda comanda = buscarComanda(id);
        ItemCardapio item = itemRepository.findById(idItemCardapio)
                .orElseThrow(() -> new ResourceNotFoundException("ItemCardapio não encontrado com id " + idItemCardapio));
        comanda.removerPedido(item);
        return comandaRepository.save(comanda);
    }
}
