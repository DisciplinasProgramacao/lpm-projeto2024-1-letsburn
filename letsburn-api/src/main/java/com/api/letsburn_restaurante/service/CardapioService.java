package com.api.letsburn_restaurante.service;

import com.api.letsburn_restaurante.model.ItemCardapio;
import com.api.letsburn_restaurante.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CardapioService {

    @Autowired
    private ItemRepository itemCardapioRepository;

    @PostConstruct
    public void cadastrarItensCardapio() {
        List<ItemCardapio> itensCardapio = Arrays.asList(
                new ItemCardapio("Água", 3.0),
                new ItemCardapio("Copo de suco", 2.0),
                new ItemCardapio("Refrigerante orgânico", 7.0),
                new ItemCardapio("Cerveja vegana", 9.0),
                new ItemCardapio("Taça de vinho vegano", 18.0),
                new ItemCardapio("Moqueca de Palmito", 32.0),
                new ItemCardapio("Falafel Assado", 20.0),
                new ItemCardapio("Salada Primavera com Macarrão Konjac", 25.0),
                new ItemCardapio("Escondidinho de Inhame", 18.0),
                new ItemCardapio("Strogonoff de Cogumelos", 35.0),
                new ItemCardapio("Caçarola de legumes", 22.0)
        );

        for (ItemCardapio item : itensCardapio) {
            if (!itemCardapioRepository.existsByNome(item.getNome())) {
                itemCardapioRepository.save(item);
            }
        }
    }
}
