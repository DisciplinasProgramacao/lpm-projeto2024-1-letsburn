package letsburn.controller;

import letsburn.dto.RequestRequisicaoDTO;
import letsburn.entidades.Cliente;
import letsburn.entidades.Comanda;
import letsburn.entidades.Mesa;
import letsburn.entidades.Requisicao;
import letsburn.exception.ResourceNotFoundException;
import letsburn.service.ClienteService;
import letsburn.service.ComandaService;
import letsburn.service.MesaService;
import letsburn.service.RequisicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/requisicao")
public class RequisicaoController {

    @Autowired
    private RequisicaoService requisicaoService;

    @Autowired
    private MesaService mesaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ComandaService comandaService;

    @PostMapping
    public Requisicao criarRequisicao(@RequestBody RequestRequisicaoDTO requisicaoDTO) {
        Mesa mesa = mesaService.buscarMesa(requisicaoDTO.mesaId())
                .orElseThrow(() -> new ResourceNotFoundException("Mesa não encontrada"));
        Cliente cliente = clienteService.buscarCliente(requisicaoDTO.clienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        Comanda comanda = comandaService.buscarComanda(requisicaoDTO.comandaId())
                .orElseThrow(() -> new ResourceNotFoundException("Comanda não encontrada"));

        var requisicao = new Requisicao(requisicaoDTO.qtdPessoas(), mesa, cliente, comanda);
        return requisicaoService.criarRequisicao(requisicao);
    }

    @GetMapping
    public List<Requisicao> listarRequisicoes() {
        return requisicaoService.listarRequisicoes();
    }

    @GetMapping("/{id}")
    public Optional<Requisicao> buscarRequisicao(@PathVariable Long id) {
        return requisicaoService.buscarRequisicao(id);
    }

    @GetMapping("/fechar-conta/{id}")
    public void fecharConta(@PathVariable Long id) {
        requisicaoService.fecharConta(id);
    }
}
