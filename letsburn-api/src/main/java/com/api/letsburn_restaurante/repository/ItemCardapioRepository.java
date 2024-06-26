package com.api.letsburn_restaurante.repository;

import com.api.letsburn_restaurante.model.ItemCardapio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Long> {
    Optional<ItemCardapio> findByNome(String nome);
}
