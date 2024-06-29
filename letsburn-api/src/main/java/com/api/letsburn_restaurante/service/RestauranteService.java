package com.api.letsburn_restaurante.service;

import com.api.letsburn_restaurante.dto.RequestAtenderClienteDTO;
import com.api.letsburn_restaurante.dto.ResponseComanda;
import com.api.letsburn_restaurante.exception.NumeroDePessoasExcedidoException;
import com.api.letsburn_restaurante.exception.MesasOcupadasException;
import com.api.letsburn_restaurante.model.Cliente;
import com.api.letsburn_restaurante.model.Comanda;
import com.api.letsburn_restaurante.model.Mesa;
import com.api.letsburn_restaurante.model.Requisicao;
import com.api.letsburn_restaurante.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @PostConstruct
    public void init() {
        criarMesas();
    }

    public Long atenderCliente(RequestAtenderClienteDTO atenderClienteDTO) {
        System.out.println("Atendendo cliente...");
        if (atenderClienteDTO.qtdPessoas() > 8) {
            throw new NumeroDePessoasExcedidoException("O número de pessoas não pode ser maior que 8.");
        }

        Optional<Mesa> optionalMesa = mesaService.buscarMelhorMesaDisponivel(atenderClienteDTO.qtdPessoas());
        if (!optionalMesa.isPresent()) {
            throw new MesasOcupadasException("Todas as mesas de "+ atenderClienteDTO.qtdPessoas() + " pessoas estão ocupadas. Por favor, aguarde.");
        }

        Cliente cliente = new Cliente(atenderClienteDTO.nome());
        clienteService.cadastrarCliente(cliente);

        Mesa mesa = optionalMesa.get();
        Requisicao requisicao = new Requisicao(atenderClienteDTO.qtdPessoas(), mesa, cliente, true);
        requisicaoService.criarRequisicao(requisicao);

        return requisicao.getId();
    }

    public void fazerPedido(Long id, Long idItemCardapio) {
        Requisicao requisicao = requisicaoService.buscarRequisicao(id).get();
        requisicao.adicionarPedido(itemRepository.findById(idItemCardapio).get());
        requisicaoService.atualizarRequisicao(requisicao.getId(), requisicao);
        System.out.println("Pedido adicionado à comanda.");
    }

    public ResponseComanda fecharConta(Long id) {
        Requisicao requisicao = requisicaoService.fecharConta(id);
        Comanda comanda = requisicao.getComanda();
        double valorTotal = comanda.calcularValorTotal();
        int qtdPessoas = requisicao.getQtdPessoas();
        double valorPorCliente = comanda.calcularValorPorCliente(qtdPessoas);

        return new ResponseComanda(comanda.getId(), valorTotal, qtdPessoas, valorPorCliente);
    }

    public List<Requisicao> requisicoes(Boolean ativa) {
        return requisicaoService.listarRequisicoes(ativa);
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
