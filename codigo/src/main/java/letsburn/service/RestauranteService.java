package letsburn.service;

import letsburn.dto.RequestAtenderClienteDTO;
import letsburn.dto.ResponseComanda;
import letsburn.entidades.Cliente;
import letsburn.entidades.Comanda;
import letsburn.entidades.Mesa;
import letsburn.entidades.Requisicao;
import letsburn.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired ComandaService comandaService;

    @Autowired
    ItemRepository itemRepository;

    private final Queue<Requisicao> listaDeEspera = new LinkedList<>();

    public Long atenderCliente(RequestAtenderClienteDTO atenderClienteDTO) {
        System.out.println("Atendendo cliente...");
        Cliente cliente = new Cliente(atenderClienteDTO.nome());
        clienteService.cadastrarCliente(cliente);

        Optional<Mesa> optionalMesa = mesaService.buscarMelhorMesaDisponivel(atenderClienteDTO.qtdPessoas());
        Requisicao requisicao = optionalMesa.map(mesa -> new Requisicao(atenderClienteDTO.qtdPessoas(), mesa, cliente, true))
                .orElseGet(() -> new Requisicao(atenderClienteDTO.qtdPessoas(), null, cliente, false));

        if (!optionalMesa.isPresent()) {
            adicionarAListaDeEspera(requisicao);
        }
        else{
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

}
