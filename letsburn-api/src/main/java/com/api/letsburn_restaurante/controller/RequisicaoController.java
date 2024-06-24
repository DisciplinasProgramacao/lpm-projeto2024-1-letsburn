package com.api.letsburn_restaurante.controller;

import com.api.letsburn_restaurante.model.Requisicao;
import com.api.letsburn_restaurante.service.RequisicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requisicoes")
public class RequisicaoController {

    @Autowired
    private RequisicaoService requisicaoService;

    @PostMapping
    public ResponseEntity<Requisicao> criarRequisicao(@RequestBody Requisicao requisicao) {
        Requisicao novaRequisicao = requisicaoService.criarRequisicao(requisicao);
        return ResponseEntity.ok(novaRequisicao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Requisicao> atualizarRequisicao(@PathVariable Long id, @RequestBody Requisicao requisicao) {
        Optional<Requisicao> requisicaoAtualizada = requisicaoService.atualizarRequisicao(id, requisicao);
        return requisicaoAtualizada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Requisicao>> listarRequisicoes(@RequestParam(value = "ativa", required = false) Boolean ativa) {
        List<Requisicao> requisicoes = requisicaoService.listarRequisicoes(ativa);
        return ResponseEntity.ok(requisicoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Requisicao> buscarRequisicao(@PathVariable Long id) {
        Optional<Requisicao> requisicao = requisicaoService.buscarRequisicao(id);
        return requisicao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/fechar")
    public ResponseEntity<Void> fecharRequisicao(@PathVariable Long id) {
        Optional<Requisicao> requisicao = requisicaoService.buscarRequisicao(id);
        if (requisicao.isPresent()) {
            requisicaoService.fecharConta(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
