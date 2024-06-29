package com.api.letsburn_restaurante.model;

import jakarta.persistence.*;

@Entity
public class ItemCardapio extends Item {

    public ItemCardapio(String nome, Double preco) {
        super(nome, preco);
    }

    public ItemCardapio() {
    }
}
