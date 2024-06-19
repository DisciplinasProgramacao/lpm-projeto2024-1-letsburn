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
    private List<ItemCardapio> pedidos;

    public Comanda() {
    }

    public Comanda(List<ItemCardapio> pedidos) {
        this.pedidos = pedidos;
    }

    public Long getId() {
        return id;
    }

    public List<ItemCardapio> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<ItemCardapio> pedidos) {
        this.pedidos = pedidos;
    }

    public void adicionarPedido(ItemCardapio item) {
        this.pedidos.add(item);
    }

    public void removerPedido(ItemCardapio item) {
        this.pedidos.remove(item);
    }

    public double calcularValorTotal() {
        return aplicarTaxa(this.getPedidos().stream().mapToDouble(Item::getPreco).sum());
    }

    public double calcularValorPorCliente(int numPessoas) {
        double valorTotal = calcularValorTotal();
        return valorTotal / numPessoas;
    }

    public double aplicarTaxa(Double precoTotal) {
        return precoTotal + (precoTotal * 0.10);
    }
}
