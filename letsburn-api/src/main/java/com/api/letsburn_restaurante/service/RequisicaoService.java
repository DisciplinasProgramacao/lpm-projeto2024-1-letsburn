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

    public void atualizarRequisicao(Requisicao requisicao) {
        requisicao.atualizar(requisicaoRepository);
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
        return Requisicao.buscarPorId(requisicaoRepository, id);
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
