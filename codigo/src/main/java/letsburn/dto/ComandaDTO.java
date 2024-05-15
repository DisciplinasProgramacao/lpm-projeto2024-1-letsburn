package letsburn.dto;


import java.util.List;

/**
 * Record for transferring comanda data including a list of item IDs.
 */
public record ComandaDTO(Double valorTotal,List<Integer> itemIds) {
}
