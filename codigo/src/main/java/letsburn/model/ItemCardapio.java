package letsburn.model;

public class ItemCardapio {
    private final String nome;
    private final double preco;

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public ItemCardapio(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }
}
