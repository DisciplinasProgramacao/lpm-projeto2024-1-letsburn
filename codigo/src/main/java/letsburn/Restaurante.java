package letsburn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Restaurante {
    private final List<Mesa> mesas;
    private final List<Requisicao> requisicoesAtivas = new ArrayList<>();
    private final List<Requisicao> requisicoesEmEspera = new ArrayList<>();

    public Restaurante(List<Mesa> mesas) {
        this.mesas = mesas;
    }

    /**
     * Gera a requisição
     * @param cliente: cliente
     * @param quantidadePessoas: quantidade de pessoas
     */
    public void gerarRequisicao(Cliente cliente, int quantidadePessoas) {
        Optional<Mesa> optionalMesa = recuperarMesaDisponivel(quantidadePessoas);
        if (optionalMesa.isPresent()) {
            alocarRequisicaoAtiva(new Requisicao(cliente, optionalMesa.get(), quantidadePessoas));
        } else {
            alocarRequisicaoEmEspera(new Requisicao(cliente, quantidadePessoas));
        }
    }

    /**
     * Libera a requisição
     * @param cliente: cliente
     */
    public void liberarRequisicao(Cliente cliente) {
        Requisicao req = desalocarRequisicaoAtiva(cliente);
        if(req!=null)
            req.encerrar();
        // TODO: Repensar se usaremos uma lista ou duas
//        Mesa mesaLiberada = desalocarRequisicaoAtiva(cliente);
//        Optional<Requisicao> optionalRequisicaoEmEspera = buscarRequisicaoEmEspera(mesaLiberada);
//        if (optionalRequisicaoEmEspera.isPresent()) {
//            Requisicao requisicao = optionalRequisicaoEmEspera.get();
//            alocarRequisicaoAtiva(requisicao);
//            desalocarRequisicaoEmEspera(requisicao.getId());
//        }
    }

    /**
     * Recupera mesa dispponível que atende ao critério
     * @param capacidade: capacidade
     * @return Optional<Mesa>
     */
    private Optional<Mesa> recuperarMesaDisponivel(int capacidade) {
        return this.mesas.stream().filter(m -> m.getCapacidade() >= capacidade).min(Comparator.comparingInt(Mesa::getCapacidade));
    }

    /** Aloca requisição na lista de requisições ativas
     * @param requisicao: requisição de alocação
     */
    private void alocarRequisicaoAtiva(Requisicao requisicao) {
        this.requisicoesAtivas.add(requisicao);
    }

    /** Aloca requisição na lista de espera
     * @param requisicao: requisição de alocação
     */
    private void alocarRequisicaoEmEspera(Requisicao requisicao) {
        this.requisicoesEmEspera.add(requisicao);
    }

    /** Desaloca requisição na lista de requisições ativas
     * @param cliente: cliente
     * @return Mesa
     */
    private Requisicao desalocarRequisicaoAtiva(Cliente cliente) {
        return this.requisicoesAtivas.stream().map(r -> {
            if (r.getCliente().getId() == cliente.getId()) this.requisicoesAtivas.remove(r);
            return r;
        }).findFirst().orElse(null);
    }

    /** Desaloca requisição na lista de espera
     * @param id: id da requisição a ser desalocada
     */
    private void desalocarRequisicaoEmEspera(String id) {
        this.requisicoesEmEspera.remove(this.requisicoesEmEspera.stream().filter(r -> r.getId().equals(id)).findFirst().get());
    }

    /** Busca requisicao na lista de espera que possa ser atendida por uma mesa liberada
     * @param mesa: mesa
     * @return Optional<Requisicao>
     */
    private Optional<Requisicao> buscarRequisicaoEmEspera(Mesa mesa) {
        return this.requisicoesEmEspera.stream().filter(r -> r.getQuantidadePessoas() <= mesa.getCapacidade()).findFirst();
    }

    /** Exibe lista de espera
     */
    public void exibirListaEspera() {
        System.out.println("Lista de espera:");
        this.requisicoesEmEspera.forEach(r -> System.out.println(r.getCliente().getNome()));
    }
}
