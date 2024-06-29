package com.api.letsburn_restaurante.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.letsburn_restaurante.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    boolean existsByNome(String nome);

    Optional<Item> findByNome(String nome);
}
