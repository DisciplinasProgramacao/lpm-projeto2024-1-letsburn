package letsburn.controller;

import letsburn.dto.RequestClientDTO;
import letsburn.entidades.Cliente;
import letsburn.repository.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Classe que expõe endpoints de API para gerenciamento de Itens no sistema.
 */
@RestController
@RequestMapping("/ItemCardapio")  // Define a base URL para todos os métodos deste controlador.
public class ItemCardpioController {
    @Autowired
    private ItemRepository ItemRepository;

    /**
     * Cria um novo cardapio com base nos dados fornecidos.
     *
     * @param requestItensDTO Dados do Itens para criação.
     * @return Retorna um ResponseEntity com o URI dos Itens criado e status HTTP 201.
     */
    @PostMapping
    public ResponseEntity<Object> cadastrarItens(@RequestBody RequestItensDTO requestItensDTO) {
        var ItemCardapioModel = new Itens();
        BeanUtils.copyProperties(requestItensDTO, ItesModel);
        ItensRepository.save(ItensCardapioModel);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(ItensModel.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Lista todos os Itens cadastrados no sistema.
     *
     * @return Lista de itens.
     */
    @GetMapping
    public List<Itens> listarItens() {
        return ItensRepository.findAll();
    }

    /**
     * Busca um Item pelo ID fornecido.
     *
     * @param id Identificador do Item a ser buscado.
     * @return Retorna um ResponseEntity contendo o Item encontrado ou status HTTP 404 se não encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Item> buscarItens(@PathVariable Long id) {
        return itemRepository.findById(id)
                .map(Itens-> ResponseEntity.ok().body(Itens))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Remove um Item do sistema com base no ID fornecido.
     *
     * @param id O ID dos Items a ser removido.
     * @return Retorna um ResponseEntity com status HTTP 200 se deletado com sucesso ou status HTTP 404 se não encontrado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarItemCardapio(@PathVariable Long id) {
        return ItemRepository.findById(id)
                .map(item -> {
                    ItemRepository.delete(item);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}