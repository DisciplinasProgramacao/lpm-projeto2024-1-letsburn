package com.api.letsburn_restaurante.dto;

public record RequestAtenderClienteDTO(
        Long idCliente,
        String nome,
        int qtdPessoas
) {

}
