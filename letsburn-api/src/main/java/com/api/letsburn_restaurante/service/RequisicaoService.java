package com.api.letsburn_restaurante.service;

import com.api.letsburn_restaurante.exception.ResourceNotFoundException;
import com.api.letsburn_restaurante.model.Comanda;
import com.api.letsburn_restaurante.model.ItemCardapio;
import com.api.letsburn_restaurante.model.Requisicao;
import com.api.letsburn_restaurante.repository.RequisicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RequisicaoService {

    @Autowired
    private RequisicaoRepository requisicaoRepository;

    public Requisicao criarRequisicao(Requisicao requisicao) {
        if (requisicao.getQtdPessoas() <= 0) {
            requisicao.setQtdPessoas(1);
        } else if (requisicao.getQtdPessoas() > 8) {
            requisicao.setQtdPessoas(8);
        }

        requisicao.getMesa().setOcupada(true);

        return requisicaoRepository.save(requisicao);
    }

    public Optional<Requisicao> atualizarRequisicao(Long id, Requisicao requisicaoAtualizada) {
        Optional<Requisicao> requisicaoExistente = requisicaoRepository.findById(id);
        if (requisicaoExistente.isPresent()) {
            Requisicao requisicao = requisicaoExistente.get();
            requisicao.setHorarioEntrada(requisicaoAtualizada.getHorarioEntrada());
            requisicao.setHorarioSaida(requisicaoAtualizada.getHorarioSaida());
            requisicao.setQtdPessoas(requisicaoAtualizada.getQtdPessoas());
            requisicao.setAtiva(requisicaoAtualizada.isAtiva());
            requisicao.setMesa(requisicaoAtualizada.getMesa());
            requisicao.setCliente(requisicaoAtualizada.getCliente());
            requisicao.setComanda(requisicaoAtualizada.getComanda());
            requisicaoRepository.save(requisicao);
            return Optional.of(requisicao);
        } else {
            throw new ResourceNotFoundException("Requisicao não encontrada com id " + id);
        }
    }

    public List<Requisicao> listarRequisicoes(Boolean ativa) {
        if (ativa == null) {
            return requisicaoRepository.findAll();
        }
        if (ativa == true) {
            return requisicaoRepository.findAllByAtivaTrue();
        }
        return requisicaoRepository.findAllByAtivaFalse();
    }

    public Optional<Requisicao> buscarRequisicao(Long id) {
        return requisicaoRepository.findById(id);
    }

    public Comanda adicionaPedido(Requisicao requisicao, ItemCardapio item) {
        if (requisicao != null && requisicao.isAtiva()) {
            requisicao.adicionarPedido(item);
            requisicaoRepository.save(requisicao);
            return requisicao.getComanda();
        }
        return null;
    }

    //todo
    // add exption quando nao encontrar requisicapo
    public Requisicao fecharConta(Long id) {
        Optional<Requisicao> requisicaoOptional = requisicaoRepository.findById(id);
        if (requisicaoOptional.isPresent()) {
            Requisicao requisicao = requisicaoOptional.get();
            requisicao.setAtiva(false);
            requisicao.setHorarioSaida(LocalDateTime.now());
            requisicao.getMesa().setOcupada(false);
            requisicaoRepository.save(requisicao);
            return requisicaoOptional.get();
        }
        return null;
    }

}
