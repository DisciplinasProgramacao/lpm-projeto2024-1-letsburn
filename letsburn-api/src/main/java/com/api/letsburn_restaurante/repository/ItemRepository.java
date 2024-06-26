package com.api.letsburn_restaurante.repository;

import java.util.Optional;

import com.api.letsburn_restaurante.model.ItemCardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemCardapio, Long> {
    boolean existsByNome(String nome);

    Optional<ItemCardapio> findByNome(String nome);
}
