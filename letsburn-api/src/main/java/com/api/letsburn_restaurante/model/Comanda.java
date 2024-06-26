package com.api.letsburn_restaurante.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "comandas")
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "comanda_item", joinColumns = @JoinColumn(name = "comanda_id"), inverseJoinColumns = @JoinColumn(name = "item_cardapio_id"))
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

    public void removerPedido(Item item) {
        this.pedidos = this.pedidos.stream()
                .filter(pedido -> !pedido.equals(item))
                .collect(Collectors.toList());
    }

    public double calcularValorTotal() {
        return aplicarTaxa(this.pedidos.stream().mapToDouble(Item::getPreco).sum());
    }

    public double calcularValorPorCliente(int numPessoas) {
        double valorTotal = calcularValorTotal();
        return valorTotal / numPessoas;
    }

    public double aplicarTaxa(Double precoTotal) {
        return precoTotal + (precoTotal * 0.10);
    }
}
