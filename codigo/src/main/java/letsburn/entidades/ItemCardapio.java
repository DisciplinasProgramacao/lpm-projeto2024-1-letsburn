package letsburn.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemCardapio {
    @Id
    private Long id;
    private String nome;
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "comanda_id")
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

    public void setId(Long id) {
        this.id = id;
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

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }
}