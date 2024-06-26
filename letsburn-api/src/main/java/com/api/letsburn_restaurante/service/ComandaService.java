package com.api.letsburn_restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.api.letsburn_restaurante.exception.ResourceNotFoundException;
import com.api.letsburn_restaurante.model.Comanda;
import com.api.letsburn_restaurante.model.ItemCardapio;
import com.api.letsburn_restaurante.model.Requisicao;
import com.api.letsburn_restaurante.repository.ComandaRepository;
import com.api.letsburn_restaurante.repository.ItemRepository;
import com.api.letsburn_restaurante.repository.RequisicaoRepository;

@Service
public class ComandaService {

    @Autowired
    private ComandaRepository comandaRepository;

    @Autowired
    private RequisicaoRepository requisicaoRepository;
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
    public void fecharComanda(@PathVariable Long id) {
        Comanda comanda = buscarComanda(id);
        Requisicao req = requisicaoRepository.findByComanda_Id(id);
        req.fecharConta();
    }

}
