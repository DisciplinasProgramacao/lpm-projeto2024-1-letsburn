
package letsburn.model;

/**
 * Criação da Classe Cliente do restaurante lestburn
 */

public class Cliente {
    private static int proximoId = 1;
    private int id;
    private String nome;

    /**
     * Construtor da classe Cliente.
     * @param id   Identificador da Cliente.
     * @param nome Nome do Cliente.
     */

    /**
     * Construtor para criar um cliente com ID sequencial
     * @param nome      Identifica o nome do cliente
     */

    public Cliente(String nome) {
        this.id = proximoId;
        this.nome = nome;
        proximoId++;
    }

    /**
     * Getter para obter o ID do cliente.
     * @return o id do cliente.
     */
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
