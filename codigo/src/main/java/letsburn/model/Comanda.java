package letsburn.model;

import java.util.List;

public class Comanda {
    private final List<ItemCardapio> pedidos;
    private static final double taxa = 0.10;

    public Comanda(List<ItemCardapio> pedidos) {
        this.pedidos = pedidos;
    }

    public double calcularTotal() {
        double precoTotal = pedidos.stream().mapToDouble(ItemCardapio::getPreco).sum();
        return aplicarTaxa(precoTotal);
    }

    public double calcultarTotalPorPessoa(int numeroPessoas) {
        return calcularTotal() / numeroPessoas;
    }

    private double aplicarTaxa(Double precoTotal) {
        return precoTotal + (precoTotal * taxa);
    }
}
