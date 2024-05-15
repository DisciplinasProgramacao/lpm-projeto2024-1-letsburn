package letsburn.repository;


import letsburn.entidades.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data repository for the Mesa entity.
 */
public interface MesaRepository extends JpaRepository<Mesa, Integer> {
}
