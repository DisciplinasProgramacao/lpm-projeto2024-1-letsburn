package com.api.letsburn_restaurante.service;

import com.api.letsburn_restaurante.dto.RequestAtenderClienteDTO;
import com.api.letsburn_restaurante.dto.RequestPedido;
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

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

/**
 * Serviço responsável por gerenciar as operações do restaurante.
 */
@Service
public class RestauranteService {

    @Autowired
    private MesaService mesaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private RequisicaoService requisicaoService;

    @Autowired
    private ItemRepository itemRepository;

    private final Queue<Requisicao> listaDeEspera = new LinkedList<>();

    /**
     * Inicializa o serviço criando mesas no restaurante.
     */
    @PostConstruct
    public void init() {
        criarMesas();
    }

    public Long atenderCliente(RequestAtenderClienteDTO atenderClienteDTO) {
        System.out.println("Atendendo cliente...");
        validarNumeroDePessoas(atenderClienteDTO.qtdPessoas());

        Cliente cliente = criarCliente(atenderClienteDTO.idCliente(), atenderClienteDTO.nome());
        Optional<Mesa> optionalMesa = mesaService.buscarMelhorMesaDisponivel(atenderClienteDTO.qtdPessoas());

        Requisicao requisicao = criarRequisicao(atenderClienteDTO.qtdPessoas(), optionalMesa, cliente);
        processarRequisicao(requisicao, optionalMesa);

        return requisicao.getId();
    }

    /**
     * Valida se o número de pessoas não excede o limite permitido.
     *
     * @param qtdPessoas Número de pessoas.
     * @throws NumeroDePessoasExcedidoException Se o número de pessoas exceder o limite permitido.
     */
    private void validarNumeroDePessoas(int qtdPessoas) {
        if (qtdPessoas > 8) {
            throw new NumeroDePessoasExcedidoException("O número de pessoas não pode ser maior que 8.");
        }

        Cliente cliente = new Cliente(atenderClienteDTO.nome());
        clienteService.cadastrarCliente(cliente);

        Optional<Mesa> optionalMesa = mesaService.buscarMelhorMesaDisponivel(atenderClienteDTO.qtdPessoas());
        Requisicao requisicao = optionalMesa
                .map(mesa -> new Requisicao(atenderClienteDTO.qtdPessoas(), mesa, cliente, true))
                .orElseGet(() -> new Requisicao(atenderClienteDTO.qtdPessoas(), null, cliente, false));

    /**
     * Processa a requisição, adicionando-a à lista de espera se necessário.
     *
     * @param requisicao Requisição a ser processada.
     * @param optionalMesa Mesa disponível (se houver).
     */
    private void processarRequisicao(Requisicao requisicao, Optional<Mesa> optionalMesa) {
        if (optionalMesa.isPresent()) {
            requisicaoService.criarRequisicao(requisicao);
        } else {
            adicionarAListaDeEspera(requisicao);
        }
    }

    /**
     * Faz um pedido baseado na requisição e nome do item.
     *
     * @param requestPedido Informações do pedido.
     * @return Comanda atualizada com o pedido.
     */
    public Comanda fazerPedido(RequestPedido requestPedido) {
        Requisicao requisicao = requisicaoService.buscarRequisicao(requestPedido.idRequisicao())
                .orElseThrow(() -> new IllegalArgumentException("Requisição não encontrada"));

        return requisicaoService.adicionaPedido(requisicao,
                itemRepository.findByNome(requestPedido.nome())
                        .orElseThrow(() -> new IllegalArgumentException("Item não encontrado")));
    }

    /**
     * Adiciona uma requisição à lista de espera.
     *
     * @param requisicao Requisição a ser adicionada.
     */
    private void adicionarAListaDeEspera(Requisicao requisicao) {
        listaDeEspera.add(requisicao);
    }

    /**
     * Fecha a conta de uma requisição, calculando o valor total e valor por cliente.
     *
     * @param id ID da requisição.
     * @return Resposta da comanda contendo os detalhes do fechamento.
     */
    public ResponseComanda fecharConta(Long id) {
        Requisicao requisicao = requisicaoService.fecharConta(id);

        Comanda comanda = requisicao.getComanda();
        double valorTotal = comanda.calcularValorTotal();
        int qtdPessoas = requisicao.getQtdPessoas();
        double valorPorCliente = comanda.calcularValorPorCliente(qtdPessoas);

        verificarListaDeEspera();
        return new ResponseComanda(comanda.getId(), valorTotal, qtdPessoas, valorPorCliente);
    }

    public List<Requisicao> requisicoes(Boolean ativa) {
        return requisicaoService.listarRequisicoes(ativa);
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

    /**
     * Cria mesas no restaurante se não houver nenhuma mesa existente.
     */
    private void criarMesas() {
        if (mesaService.contarMesas() == 0) {
            adicionarMesas(4, 4);
            adicionarMesas(4, 6);
            adicionarMesas(2, 8);
        }
    }

    /**
     * Adiciona mesas ao restaurante.
     *
     * @param quantidade Quantidade de mesas.
     * @param capacidade Capacidade de cada mesa.
     */
    private void adicionarMesas(int quantidade, int capacidade) {
        for (int i = 0; i < quantidade; i++) {
            Mesa mesa = new Mesa(capacidade);
            mesaService.criarMesa(mesa);
        }
    }
}
