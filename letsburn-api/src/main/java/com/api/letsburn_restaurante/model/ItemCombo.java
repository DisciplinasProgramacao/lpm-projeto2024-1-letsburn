package com.api.letsburn_restaurante.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public abstract class ItemCombo extends Item {
    @ManyToMany(mappedBy = "itens")
    private List<ItemCardapio> itens;
    
    @ManyToMany(mappedBy = "pedidos")
    private List<Comanda> comandas;

    public ItemCombo(String nome, Double preco) {
        super(nome, preco);
    }

    public ItemCombo() {
    }

    public List<ItemCardapio> getItens() {
        return itens;
    }

    public void setItens(List<ItemCardapio> itens) {
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("A lista de itens não pode ser nula ou vazia.");
        }
        this.itens = itens;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        if (comandas == null || comandas.isEmpty()) {
            throw new IllegalArgumentException("A lista de comandas não pode ser nula ou vazia.");
        }
        this.comandas = comandas;
    }
}
