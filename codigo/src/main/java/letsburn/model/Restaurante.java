package letsburn.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Restaurante {
    private final List<Mesa> mesas;
    private final List<Requisicao> requisicoes = new ArrayList<>();

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
        Requisicao requisicao = optionalMesa.map(mesa -> new Requisicao(cliente, mesa, quantidadePessoas, true))
                .orElseGet(() -> new Requisicao(cliente, quantidadePessoas, false));
            alocarRequisicao(new Requisicao(cliente, optionalMesa.get(), quantidadePessoas, true));
        alocarRequisicao(requisicao);
    }

    /**
     * Libera a requisição
     * @param cliente: cliente
     */
    public void liberarRequisicao(Cliente cliente) {
        Optional<Requisicao> requisicaoOptional = buscarRequisicaoPorCliente(cliente);
        if (requisicaoOptional.isEmpty()) {
            throw new RuntimeException("Requisicao invalida");
        }

        Requisicao requisicao = requisicaoOptional.get();
        requisicao.encerrar();
        buscarRequisicaoEmEspera(requisicao.getMesa()).ifPresent(r -> {r.atualizarStatus(true);});
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
    private void alocarRequisicao(Requisicao requisicao) {
        this.requisicoes.add(requisicao);
    }

    /** Desaloca requisição na lista de requisições ativas
     * @param cliente: cliente
     * @return Mesa
     */
    private Optional<Requisicao> buscarRequisicaoPorCliente(Cliente cliente) {
        return this.requisicoes.stream().filter(r -> r.getCliente().getId() == cliente.getId()).findFirst();
    }


    /** Busca requisicao na lista de espera que possa ser atendida por uma mesa liberada
     * @param mesa: mesa
     * @return Optional<Requisicao>
     */
    private Optional<Requisicao> buscarRequisicaoEmEspera(Mesa mesa) {
        return this.requisicoes.stream().filter(r -> !r.isAtiva()  && r.getQtdPessoas() <= mesa.getCapacidade()).findFirst();
    }

    /** Exibe lista de espera
     */
    public void exibirListaEspera() {
        System.out.println("Lista de espera:");
        this.requisicoes.stream().filter(r -> !r.isAtiva()).forEach(r -> System.out.println(r.getCliente().getNome()));
    }
}
