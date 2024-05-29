package letsburn.dto;

public record ResponseComanda(
        Long id,
        double valorTotal,
        int numPessoas,
        double valorPorCliente
) {

}
