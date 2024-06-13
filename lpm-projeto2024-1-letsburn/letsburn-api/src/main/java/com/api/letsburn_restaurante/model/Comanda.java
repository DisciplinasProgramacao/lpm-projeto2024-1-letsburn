package com.api.letsburn_restaurante.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "comandas")
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "comanda_item_cardapio", joinColumns = @JoinColumn(name = "comanda_id"), inverseJoinColumns = @JoinColumn(name = "item_cardapio_id"))
    private List<Item> pedidos;

    public Comanda() {
    }

    public Comanda(List<Item> pedidos) {
        this.pedidos = pedidos;
    }

    public Long getId() {
        return id;
    }

    public List<Item> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Item> pedidos) {
        this.pedidos = pedidos;
    }

    public void adicionarPedido(Item item) {
        this.pedidos.add(item);
    }
}
