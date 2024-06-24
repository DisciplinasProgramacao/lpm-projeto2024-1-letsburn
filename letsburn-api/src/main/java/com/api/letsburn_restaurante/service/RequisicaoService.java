package com.api.letsburn_restaurante.service;

import com.api.letsburn_restaurante.model.Requisicao;
import com.api.letsburn_restaurante.repository.RequisicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequisicaoService {

    @Autowired
    private RequisicaoRepository requisicaoRepository;

    public Requisicao criarRequisicao(Requisicao requisicao) {
        requisicao.prepararRequisicao();
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
            return Optional.empty();
        }
    }

    public List<Requisicao> listarRequisicoes(Boolean ativa) {
        if (ativa == null) {
            return requisicaoRepository.findAll();
        }
        if (ativa) {
            return requisicaoRepository.findAllByAtivaTrue();
        }
        return requisicaoRepository.findAllByAtivaFalse();
    }

    public Optional<Requisicao> buscarRequisicao(Long id) {
        return requisicaoRepository.findById(id);
    }

    public void fecharConta(Long id) {
        Optional<Requisicao> requisicaoOptional = buscarRequisicao(id);
        if (requisicaoOptional.isPresent()) {
            Requisicao requisicao = requisicaoOptional.get();
            requisicao.fecharConta();
            requisicaoRepository.save(requisicao);
        }
    }
}
