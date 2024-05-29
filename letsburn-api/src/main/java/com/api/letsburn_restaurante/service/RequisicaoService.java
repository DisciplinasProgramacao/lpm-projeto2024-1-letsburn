package com.api.letsburn_restaurante.service;

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

    @Autowired
    ComandaService comandaService;

    public Requisicao criarRequisicao(Requisicao requisicao) {
        if (requisicao.getQtdPessoas() <= 0) {
            requisicao.setQtdPessoas(1);
        } else if (requisicao.getQtdPessoas() > 8) {
            requisicao.setQtdPessoas(8);
        }

        requisicao.getMesa().setOcupada(true);

        return requisicaoRepository.save(requisicao);
    }

    public void atualizarRequisicao(Requisicao requisicao) {
        requisicaoRepository.save(requisicao);
    }
    public List<Requisicao> listarRequisicoes() {
        return requisicaoRepository.findAll();
    }

    public Optional<Requisicao> buscarRequisicao(Long id) {
        return requisicaoRepository.findById(id);
    }

    public Comanda adicionaPedido(Requisicao requisicao, ItemCardapio item){
         if(requisicao != null && requisicao.isAtiva()){
              requisicao.adicionarPedido(item);
              requisicaoRepository.save(requisicao);
              return requisicao.getComanda();
         }
         return null;
    }

    public void fecharConta(Long id) {
        Optional<Requisicao> requisicaoOptional = requisicaoRepository.findById(id);
        if (requisicaoOptional.isPresent()) {
            Requisicao requisicao = requisicaoOptional.get();
            requisicao.setAtiva(false);
            requisicao.setHorarioSaida(LocalDateTime.now());
            requisicao.getMesa().setOcupada(false);
            requisicaoRepository.save(requisicao);
        }
    }

    public double exibirValorPorCliente(Requisicao requisicao) {
        return comandaService.calcularValorPorCliente(requisicao.getComanda(), requisicao.getQtdPessoas());
    }
}
