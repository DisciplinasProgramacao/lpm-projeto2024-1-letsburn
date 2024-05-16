package letsburn.model;

public class ItemCardapio {
    private final String nome;
    private final Double preco;

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public ItemCardapio(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }
}
