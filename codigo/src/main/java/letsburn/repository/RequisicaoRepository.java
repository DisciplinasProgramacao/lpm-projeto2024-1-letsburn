package letsburn.repository;

import letsburn.entidades.Requisicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequisicaoRepository  extends JpaRepository<Requisicao, Long> {
    Requisicao findByAtivaTrue();
    Requisicao findByAtivaFalse();
    Requisicao findByComanda_Id(Long id);
}
