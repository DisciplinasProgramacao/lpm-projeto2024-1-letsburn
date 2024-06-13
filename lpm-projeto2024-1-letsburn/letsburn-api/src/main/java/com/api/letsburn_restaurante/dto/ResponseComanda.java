package com.api.letsburn_restaurante.dto;

public record ResponseComanda(
        Long id,
        double valorTotal,
        int numPessoas,
        double valorPorCliente
) {

}
