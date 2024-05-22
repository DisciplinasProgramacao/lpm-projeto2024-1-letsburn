import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import letsburn.entidades.Requisicao;
import letsburn.repository.RequisicaoRepository;
import letsburn.service.RequisicaoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RequisicaoServiceTest {

    @InjectMocks
    private RequisicaoService requisicaoService;

    @Mock
    private RequisicaoRepository requisicaoRepository;

    @Test
    public void testFecharConta() {
        Long id = 1L;
        Requisicao requisicao = new Requisicao();
        requisicao.setId(id);
        requisicao.setAtiva(true);

        when(requisicaoRepository.findById(id)).thenReturn(Optional.of(requisicao));

        requisicaoService.fecharConta(id);

        assertFalse(requisicao.isAtiva());
        verify(requisicaoRepository, times(1)).save(requisicao);
    }

    @Test
    public void testFecharContaNotFound() {
        Long id = 1L;

        when(requisicaoRepository.findById(id)).thenReturn(Optional.empty());

        requisicaoService.fecharConta(id);

        verify(requisicaoRepository, never()).save(any(Requisicao.class));
    }

    @Test
    public void testBuscarRequisicao() {
        Long id = 1L;
        Requisicao requisicao = new Requisicao();
        requisicao.setId(id);

        when(requisicaoRepository.findById(id)).thenReturn(Optional.of(requisicao));

        Optional<Requisicao> result = requisicaoService.buscarRequisicao(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    public void testBuscarRequisicaoNotFound() {
        Long id = 1L;

        when(requisicaoRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Requisicao> result = requisicaoService.buscarRequisicao(id);

        assertFalse(result.isPresent());
    }

    @Test
    public void testExibirValorPorCliente() {
        Requisicao requisicao = new Requisicao();
        // Adicione a lógica para configurar a requisição conforme necessário para o teste
        // Exemplo: requisicao.setValor(100.0);

        double valor = requisicaoService.exibirValorPorCliente(requisicao);

        // Adicione asserções baseadas na lógica do método
        // Exemplo: assertEquals(100.0, valor);
    }
}
