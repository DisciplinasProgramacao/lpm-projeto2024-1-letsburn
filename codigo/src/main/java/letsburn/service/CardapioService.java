package letsburn.service;

import jakarta.annotation.PostConstruct;
import letsburn.entidades.ItemCardapio;
import letsburn.repository.ItemRepository;
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

        itemCardapioRepository.saveAll(itensCardapio);
    }
}
