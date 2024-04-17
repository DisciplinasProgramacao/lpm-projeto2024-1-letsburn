package letsburn;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RequisicaoTest {
    
    @Test
    public void criarHorariodeSaidaNaoNulo() {
        Cliente c1 = new Cliente("Gabriel");
        Requisicao r1 = new Requisicao(c1, 5);
        Mesa m1 = new Mesa("123", 8, false);
        r1.setMesa(null);
        r1.encerrar(m1);
        assertNotNull(r1.getHorarioSaida());
    }

    @Test
    public void verificarMesaOcupadaFalso(){
        Cliente c1 = new Cliente("Gabriel");
        Mesa m2 = new Mesa("123", 8, false);
        Requisicao r2 = new Requisicao(c1, 5);
        r2.encerrar(m2);
        assertFalse(m2.isOcupada());
    }
    
}
