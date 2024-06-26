package com.api.letsburn_restaurante.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class ItemCardapio extends Item {

    public ItemCardapio(String nome, Double preco) {
        super(nome, preco);
    }

    public ItemCardapio() {
    }
}
