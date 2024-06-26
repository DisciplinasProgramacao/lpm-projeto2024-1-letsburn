package com.api.letsburn_restaurante.model;

import jakarta.persistence.Entity;

@Entity
public class MenuFechado  extends ItemCombo{
    public MenuFechado(String nome, Double preco) {
        super(nome, preco);
    }

    public MenuFechado() {
        super();
    }
}
