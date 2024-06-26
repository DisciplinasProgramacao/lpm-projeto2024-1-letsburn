package letsburn;

import letsburn.model.Cliente;
import letsburn.model.Mesa;
import letsburn.model.Restaurante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class RestauranteTest {
    @Mock
    Mesa mesa;

    @Mock
    Cliente cliente;

    @InjectMocks
    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        List<Mesa> mesas = new ArrayList<>();
        mesas.add(mesa);
        restaurante = new Restaurante(mesas);
    }

    @Test
    void gerarRequisicao() {
        when(mesa.getCapacidade()).thenReturn(2);
        when(cliente.getId()).thenReturn(1);
        assertDoesNotThrow(() -> restaurante.gerarRequisicao(cliente,2));
    }

    @Test
    void liberarRequisicao() {
        when(mesa.getCapacidade()).thenReturn(2);
        when(cliente.getId()).thenReturn(1);
        assertThrows(RuntimeException.class, () -> restaurante.liberarRequisicao(cliente));
    }
}