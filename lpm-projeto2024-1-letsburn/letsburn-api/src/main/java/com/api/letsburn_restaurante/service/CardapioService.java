package com.api.letsburn_restaurante.service;

import com.api.letsburn_restaurante.model.Cardapio;
import com.api.letsburn_restaurante.model.ItemCardapio;
import com.api.letsburn_restaurante.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CardapioService {

    private final ItemRepository itemCardapioRepository;

    public CardapioService(@Autowired ItemRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }

    @PostConstruct
    public void cadastrarItensCardapio() {

        for (Cardapio item : Cardapio.values()) {
            if (!itemCardapioRepository.existsByNome(item.getNome())) {
                itemCardapioRepository.save(new ItemCardapio(item.getNome(), item.getPreco()));
            }
        }
    }
}
