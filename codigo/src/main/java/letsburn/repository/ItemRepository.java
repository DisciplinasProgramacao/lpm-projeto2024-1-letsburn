package letsburn.repository;

import letsburn.entidades.Cliente;
import letsburn.entidades.ItemCardapio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemCardapio, Long> {

}