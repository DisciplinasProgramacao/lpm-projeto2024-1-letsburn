package com.api.letsburn_restaurante.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double preco;

    @ManyToMany(mappedBy = "pedidos")
    private List<Comanda> comandas;

    public Item(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Item() {
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
}
