package letsburn;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import letsburn.controller.RequisicaoController;
import letsburn.entidades.Requisicao;
import letsburn.service.RequisicaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RequisicaoController.class)
public class RequisicaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequisicaoService requisicaoService;

    @Test
    public void testFecharConta() throws Exception {
        Long id = 1L;
        Requisicao requisicao = new Requisicao();
        requisicao.setId(id);
        requisicao.setAtiva(false);

        when(requisicaoService.buscarRequisicao(id)).thenReturn(Optional.of(requisicao));
        when(requisicaoService.exibirValorPorCliente(requisicao)).thenReturn(100.0);

        mockMvc.perform(put("/requisicoes/fechar-conta/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("100.0"));
    }

    @Test
    public void testFecharContaNotFound() throws Exception {
        Long id = 1L;

        when(requisicaoService.buscarRequisicao(id)).thenReturn(Optional.empty());

        mockMvc.perform(put("/requisicoes/fechar-conta/{id}", id))
                .andExpect(status().isNotFound());
    }
}
