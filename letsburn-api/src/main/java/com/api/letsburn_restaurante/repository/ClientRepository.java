package com.api.letsburn_restaurante.repository;

import com.api.letsburn_restaurante.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Cliente, Long> {
}