package letsburn.entidades;

import jakarta.persistence.*;

@Entity
public class ItemCardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "comanda_id", referencedColumnName = "id")
    private Comanda comanda;

    public ItemCardapio(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public ItemCardapio() {

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

}