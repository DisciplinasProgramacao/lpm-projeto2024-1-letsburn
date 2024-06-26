package com.api.letsburn_restaurante.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class ItemCardapio extends Item {
    @ManyToMany(mappedBy = "pedidos")
    private List<Comanda> comandas;

    public ItemCardapio(String nome, Double preco) {
        super(nome, preco);
    }

    public ItemCardapio() {
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }
}
