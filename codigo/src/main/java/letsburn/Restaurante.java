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

    public void gerarRequisicao(Cliente cliente, int quantidadePessoas) {
        Optional<Mesa> optionalMesa = recuperarMesaDisponivel(quantidadePessoas);
        if (optionalMesa.isPresent()) {
            alocarRequisicaoAtiva(new Requisicao(cliente, optionalMesa.get(), quantidadePessoas));
        } else {
            alocarRequisicaoEmEspera(new Requisicao(cliente, quantidadePessoas));
        }
    }

    public void liberarRequisicao(Cliente cliente) {
        Mesa mesaLiberada = desalocarRequisicaoAtiva(cliente);
        Optional<Requisicao> optionalRequisicaoEmEspera = buscarRequisicaoEmEspera(mesaLiberada);
        if (optionalRequisicaoEmEspera.isPresent()) {
            Requisicao requisicao = optionalRequisicaoEmEspera.get();
            alocarRequisicaoAtiva(requisicao);
            desalocarRequisicaoEmEspera(requisicao.getId());
        }
    }

    private Optional<Mesa> recuperarMesaDisponivel(int capacidade) {
        return this.mesas.stream().filter(m -> m.getCapacidade() >= capacidade).min(Comparator.comparingInt(Mesa::getCapacidade));
    }

    private void alocarRequisicaoAtiva(Requisicao requisicao) {
        this.requisicoesAtivas.add(requisicao);
    }

    private void alocarRequisicaoEmEspera(Requisicao requisicao) {
        this.requisicoesEmEspera.add(requisicao);
    }

    private Mesa desalocarRequisicaoAtiva(Cliente cliente) {
        return this.requisicoesAtivas.stream().map(m -> {
            if (m.getCliente().getId() == cliente.getId()) this.requisicoesAtivas.remove(m);
            return m.getMesa();
        }).findFirst().orElseThrow(RuntimeException::new);
    }

    private void desalocarRequisicaoEmEspera(String id) {
        this.requisicoesEmEspera.remove(this.requisicoesEmEspera.stream().filter(r -> r.getId().equals(id)).findFirst().get());
    }

    private Optional<Requisicao> buscarRequisicaoEmEspera(Mesa mesa) {
        return this.requisicoesEmEspera.stream().filter(r -> r.getQuantidadePessoas() <= mesa.getCapacidade()).findFirst();
    }

}
