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

    public double calcularTotalPorPessoa(int numeroPessoas) {
        return calcularTotal() / numeroPessoas;
    }

    private double aplicarTaxa(double precoTotal) {
        return precoTotal + (precoTotal * taxa);
    }
    
    public void adicionarPedido(ItemCardapio item) {
        pedidos.add(item);
        System.out.println("Item " + item.getNome() + " adicionado à comanda.");
    }

}
