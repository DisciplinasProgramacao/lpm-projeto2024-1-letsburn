package com.api.letsburn_restaurante.repository;

import com.api.letsburn_restaurante.model.Requisicao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {
    List<Requisicao> findAllByAtivaTrue();

    List<Requisicao> findAllByAtivaFalse();

    Requisicao findByComanda_Id(Long id);
}
