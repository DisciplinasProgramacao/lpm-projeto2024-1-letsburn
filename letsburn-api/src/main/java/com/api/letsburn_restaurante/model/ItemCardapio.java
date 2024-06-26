package com.api.letsburn_restaurante.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class ItemCardapio extends Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double preco;

    @ManyToMany(mappedBy = "itens")
    private List<ItemCombo> combos;

    @ManyToMany(mappedBy = "pedidos")
    private List<Comanda> comandas;

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

    public Double getPreco() {
        return preco;
    }

    public List<ItemCombo> getCombos() {
        return combos;
    }

    public void setCombos(List<ItemCombo> combos) {
        this.combos = combos;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }
}
