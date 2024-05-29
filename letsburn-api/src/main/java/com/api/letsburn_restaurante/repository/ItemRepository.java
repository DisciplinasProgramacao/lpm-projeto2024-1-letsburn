package com.api.letsburn_restaurante.repository;

import com.api.letsburn_restaurante.model.ItemCardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemCardapio, Long> {

    boolean existsByNome(String nome);
}