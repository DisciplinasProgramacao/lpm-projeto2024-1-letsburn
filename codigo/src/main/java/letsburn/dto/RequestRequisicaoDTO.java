package letsburn.dto;

public record RequestRequisicaoDTO(
        int qtdPessoas,
        Long mesaId,
        Long clienteId,
        Long comandaId
) {
}
