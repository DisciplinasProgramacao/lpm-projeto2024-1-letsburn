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
    @JoinTable(name = "comanda_item_cardapio", joinColumns = @JoinColumn(name = "comanda_id"), inverseJoinColumns = @JoinColumn(name = "item_cardapio_id"))
    private List<ItemCardapio> pedidos;

    public Comanda() {
    }

    public Comanda(List<ItemCardapio> pedidos) {
        setPedidos(pedidos);
    }

    public Long getId() {
        return id;
    }

    public List<ItemCardapio> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<ItemCardapio> pedidos) {
        if (pedidos == null || pedidos.isEmpty()) {
            throw new IllegalArgumentException("A lista de pedidos não pode ser nula ou vazia.");
        }
        this.pedidos = pedidos;
    }

    public void adicionarPedido(ItemCardapio item) {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo.");
        }
        this.pedidos.add(item);
    }

    public void removerPedido(ItemCardapio item) {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo.");
        }
        this.pedidos = this.pedidos.stream()
                .filter(pedido -> !pedido.equals(item))
                .collect(Collectors.toList());
    }

    public double calcularValorTotal() {
        return aplicarTaxa(this.pedidos.stream().mapToDouble(ItemCardapio::getPreco).sum());
    }

    public double calcularValorPorCliente(int numPessoas) {
        if (numPessoas <= 0) {
            throw new IllegalArgumentException("O número de pessoas deve ser maior que zero.");
        }
        double valorTotal = calcularValorTotal();
        return valorTotal / numPessoas;
    }

    public double aplicarTaxa(Double precoTotal) {
        if (precoTotal == null || precoTotal < 0) {
            throw new IllegalArgumentException("O preço total não pode ser nulo ou negativo.");
        }
        return precoTotal + (precoTotal * 0.10);
    }
}
