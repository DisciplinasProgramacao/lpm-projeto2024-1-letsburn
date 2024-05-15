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
}
