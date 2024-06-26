package com.api.letsburn_restaurante.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class ItemCombo extends Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @ManyToMany
    @JoinTable(name = "item_combo", joinColumns = @JoinColumn(name = "item_combo_id"), inverseJoinColumns = @JoinColumn(name = "item_cardapio_id"))
    private final List<ItemCardapio> itens;

    public ItemCombo(String nome, Double preco, List<ItemCardapio> itens) {
        super(nome, preco);
        this.itens = itens;
    }

    public ItemCombo() {
        itens = null;
    }

    public List<ItemCardapio> getItens() {
        return itens;
    }

}
