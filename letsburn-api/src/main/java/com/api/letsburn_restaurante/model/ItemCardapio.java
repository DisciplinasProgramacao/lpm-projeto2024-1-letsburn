package com.api.letsburn_restaurante.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class ItemCardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double preco;

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }
}
