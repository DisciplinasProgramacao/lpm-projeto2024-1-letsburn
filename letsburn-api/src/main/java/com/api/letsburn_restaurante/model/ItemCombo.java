package com.api.letsburn_restaurante.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public abstract class ItemCombo extends Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double preco;

    @ManyToMany
    @JoinTable(
            name = "item_combo_cardapio",
            joinColumns = @JoinColumn(name = "item_combo_id"),
            inverseJoinColumns = @JoinColumn(name = "item_cardapio_id")
    )
    private List<ItemCardapio> itens;

    @ManyToMany(mappedBy = "pedidos")
    private List<Comanda> comandas;

    public ItemCombo(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public ItemCombo() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public List<ItemCardapio> getItens() {
        return itens;
    }

    public void setItens(List<ItemCardapio> itens) {
        this.itens = itens;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }
}
