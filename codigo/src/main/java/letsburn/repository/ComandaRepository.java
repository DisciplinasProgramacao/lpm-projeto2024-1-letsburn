package letsburn.repository;

import letsburn.entidades.Comanda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComandaRepository extends JpaRepository<Comanda, Integer> {
}
