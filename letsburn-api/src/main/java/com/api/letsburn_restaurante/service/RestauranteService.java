package com.api.letsburn_restaurante.service;

import com.api.letsburn_restaurante.dto.RequestAtenderClienteDTO;
import com.api.letsburn_restaurante.dto.ResponseComanda;
import com.api.letsburn_restaurante.exception.NumeroDePessoasExcedidoException;
import com.api.letsburn_restaurante.model.Cliente;
import com.api.letsburn_restaurante.model.Comanda;
import com.api.letsburn_restaurante.model.Mesa;
import com.api.letsburn_restaurante.model.Requisicao;
import com.api.letsburn_restaurante.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

@Service
public class RestauranteService {

    @Autowired
    private MesaService mesaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private RequisicaoService requisicaoService;

    @Autowired
    private ComandaService comandaService;

    @Autowired
    private ItemRepository itemRepository;

    private final Queue<Requisicao> listaDeEspera = new LinkedList<>();

    @PostConstruct
    public void init() {
        criarMesas();
    }
    public Long atenderCliente(RequestAtenderClienteDTO atenderClienteDTO) {
        System.out.println("Atendendo cliente...");
        if (atenderClienteDTO.qtdPessoas() > 8) {
            throw new NumeroDePessoasExcedidoException("O número de pessoas não pode ser maior que 8.");
        }

        Cliente cliente = new Cliente(atenderClienteDTO.nome());
        clienteService.cadastrarCliente(cliente);

        Optional<Mesa> optionalMesa = mesaService.buscarMelhorMesaDisponivel(atenderClienteDTO.qtdPessoas());
        Requisicao requisicao = optionalMesa.map(mesa -> new Requisicao(atenderClienteDTO.qtdPessoas(), mesa, cliente, true))
                .orElseGet(() -> new Requisicao(atenderClienteDTO.qtdPessoas(), null, cliente, false));

        if (!optionalMesa.isPresent()) {
            adicionarAListaDeEspera(requisicao);
        } else {
            requisicaoService.criarRequisicao(requisicao);
        }

        return requisicao.getId();
    }

    public void fazerPedido(Long id, Long idItemCardapio) {
        Requisicao requisicao = requisicaoService.buscarRequisicao(id).get();

        Comanda comanda = requisicaoService.adicionaPedido(requisicao, itemRepository.findById(idItemCardapio).get());
        System.out.println("Pedido adicionado à comanda.");
    }

    private void adicionarAListaDeEspera(Requisicao requisicao) {
        listaDeEspera.add(requisicao);
        System.out.println("Requisição adicionada à lista de espera.");
    }

    public ResponseComanda fecharConta(Long id) {
        requisicaoService.fecharConta(id);
        Requisicao requisicao = requisicaoService.buscarRequisicao(id).get();

        Comanda comanda = requisicao.getComanda();
        double valorTotal = comandaService.calcularValorTotal(comanda);
        int qtdPessoas = requisicao.getQtdPessoas();
        double valorPorCliente = comandaService.calcularValorPorCliente(comanda, qtdPessoas);

        verificarListaDeEspera();
        return new ResponseComanda(comanda.getId(), valorTotal, qtdPessoas, valorPorCliente);
    }

    private void verificarListaDeEspera() {
        while (!listaDeEspera.isEmpty()) {
            Requisicao requisicao = listaDeEspera.peek();
            Optional<Mesa> optionalMesa = mesaService.buscarMelhorMesaDisponivel(requisicao.getQtdPessoas());
            if (optionalMesa.isPresent()) {
                Mesa mesa = optionalMesa.get();
                mesa.setOcupada(true);
                requisicao.setMesa(mesa);
                requisicao.setAtiva(true);
                requisicaoService.atualizarRequisicao(requisicao);
                listaDeEspera.poll();
                System.out.println("Requisição atendida a partir da lista de espera.");
            } else {
                break;
            }
        }
    }

    public void criarMesas() {
        if (mesaService.contarMesas() == 0) {
            adicionarMesas(4, 4); // 4 mesas para 4 pessoas
            adicionarMesas(4, 6); // 4 mesas para 6 pessoas
            adicionarMesas(2, 8); // 2 mesas para 8 pessoas
        }
    }

    private void adicionarMesas(int quantidade, int capacidade) {
        for (int i = 0; i < quantidade; i++) {
            Mesa mesa = new Mesa(capacidade);
            mesaService.criarMesa(mesa);
        }
    }
}
