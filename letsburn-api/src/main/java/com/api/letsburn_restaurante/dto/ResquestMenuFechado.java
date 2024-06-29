package com.api.letsburn_restaurante.dto;

import java.util.List;

public record ResquestMenuFechado(
        String nome,
        Double preco,
        List<String> itens
) {
}
