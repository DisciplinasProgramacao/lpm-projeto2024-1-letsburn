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
 * Classe que expõe endpoints de API par gerenciamento de clientes no sistema.
 */
@RestController
@RequestMapping("/cliente")  // Define a base URL para todos os métodos deste controlador.
public class ClienteController {
    @Autowired
    private ClientRepository clientRepository;

    /**
     * Cria um novo cliente com base nos dados fornecidos.
     *
     * @param requestClienteDTaO Dados do cliente para criação.
     * @return Retorna um ResponseEntity com o URI do cliente criado e status HTTP 201.
     */
    @PostMapping
    public ResponseEntity<Object> cadastrarCliente(@RequestBody RequestClientDTO requestClienteDTO) {
        var clienteModel = new Cliente();
        BeanUtils.copyProperties(requestClienteDTO, clienteModel);
        clientRepository.save(clienteModel);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(clienteModel.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Lista todos os clientes cadastrados no sistema.
     *
     * @return Lista de clientes.
     */
    @GetMapping
    public List<Cliente> listarClientes() {
        return clientRepository.findAll();
    }

    /**
     * Busca um cliente pelo ID fornecido.
     *
     * @param id Identificador do cliente a ser buscado.
     * @return Retorna um ResponseEntity contendo o cliente encontrado ou status HTTP 404 se não encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(cliente -> ResponseEntity.ok().body(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Atualiza os dados de um cliente existente com base no ID fornecido.
     *
     * @param id O ID do cliente a ser atualizado.
     * @param clienteDTO Os novos dados do cliente para atualização.
     * @return Retorna um ResponseEntity contendo o cliente atualizado ou status HTTP 404 se não encontrado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody RequestClientDTO clienteDTO) {
        return clientRepository.findById(id)
                .map(clienteExistente -> {
                    BeanUtils.copyProperties(clienteDTO, clienteExistente, "id");
                    clientRepository.save(clienteExistente);
                    return ResponseEntity.ok(clienteExistente);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Remove um cliente do sistema com base no ID fornecido.
     *
     * @param id O ID do cliente a ser removido.
     * @return Retorna um ResponseEntity com status HTTP 200 se deletado com sucesso ou status HTTP 404 se não encontrado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCliente(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(cliente -> {
                    clientRepository.delete(cliente);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}