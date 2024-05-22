package letsburn.service;

import letsburn.entidades.Comanda;
import letsburn.entidades.ItemCardapio;
import letsburn.repository.ComandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComandaService {

    @Autowired
    private ComandaRepository comandaRepository;

    public Comanda criarComanda(Comanda comanda) {
        return comandaRepository.save(comanda);
    }

    public List<Comanda> listarComandas() {
        return comandaRepository.findAll();
    }

    public Optional<Comanda> buscarComanda(Long id) {
        return comandaRepository.findById(id);
    }

    public Comanda adicionarPedido(Long id, ItemCardapio item) {
        Optional<Comanda> comandaOptional = comandaRepository.findById(id);
        if (comandaOptional.isPresent()) {
            Comanda comanda = comandaOptional.get();
            comanda.getPedidos().add(item);
            return comandaRepository.save(comanda);
        }
        return null;
    }

    public Comanda removerPedido(Long id, ItemCardapio item) {
        Optional<Comanda> comandaOptional = comandaRepository.findById(id);
        if (comandaOptional.isPresent()) {
            Comanda comanda = comandaOptional.get();
            comanda.getPedidos().remove(item);
            return comandaRepository.save(comanda);
        }
        return null;
    }


    public double calcularValorTotal(Comanda comanda) {
        return comanda.getPedidos().stream().mapToDouble(ItemCardapio::getPreco).sum();
    }

    public double calcularValorPorCliente(Comanda comanda, int numPessoas) {
        double valorTotal = aplicarTaxa(calcularValorTotal(comanda));
        return valorTotal / numPessoas;
    }

    public double aplicarTaxa(Double precoTotal) {
        return precoTotal + (precoTotal * 0.10);
    }
}
