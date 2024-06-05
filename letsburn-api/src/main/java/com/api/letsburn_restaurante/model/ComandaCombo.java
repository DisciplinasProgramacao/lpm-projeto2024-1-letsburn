package com.api.letsburn_restaurante.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ComandaCombo extends Comanda {
    // todo: repensar para ser uma lista de combos e cada combo com 1 comida e 2 bebidas
    @ManyToOne
    @JoinColumn(name = "item_cardapio_id")
    private ItemCardapio comida;

    @ManyToMany
    @JoinTable(name = "bebidas_combo", joinColumns = @JoinColumn(name = "comanda_id"), inverseJoinColumns = @JoinColumn(name = "item_cardapio_id"))
    private List<ItemCardapio> bebidas;

    public ComandaCombo(ItemCardapio comida, List<ItemCardapio> bebidas) {
        if (bebidas.size() != 2) {
            throw new IllegalArgumentException("Exactly 2 bebidas must be chosen.");
        }
        this.comida = comida;
        this.bebidas = bebidas;
    }

    public ComandaCombo() {

    }
}