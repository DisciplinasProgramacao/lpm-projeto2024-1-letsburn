package com.api.letsburn_restaurante.dto;

public record ResponseComanda(
        Long idComanda,
        double valorTotal,
        int numPessoas,
        double valorPorCliente
) {

}
