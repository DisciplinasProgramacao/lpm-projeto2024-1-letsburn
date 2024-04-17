package letsburn;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private static int proximoId = 1;

    public Produto(String nome, double preco) {
        this.id = proximoId;
        this.nome = nome;
        this.preco = preco;
        proximoId++;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}
