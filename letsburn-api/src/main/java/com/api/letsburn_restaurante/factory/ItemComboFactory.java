package com.api.letsburn_restaurante.factory;

import com.api.letsburn_restaurante.model.ItemCombo;
import com.api.letsburn_restaurante.model.MenuFechado;

public class ItemComboFactory {

    public static ItemCombo createItemCombo(String type, String nome, Double preco) {
        switch (type) {
            case "MenuFechado":
                return new MenuFechado(nome, preco);
            default:
                throw new IllegalArgumentException("Tipo de ItemCombo desconhecido: " + type);
        }
    }
}