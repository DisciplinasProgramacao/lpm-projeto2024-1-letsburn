
package letsburn;

/* Criação da Classe Cliente
-id=int
-nome=string */

public class Cliente {
    private static int proximoId = 1;
    private int id;
    private String nome;


    public Cliente(String nome) {
        this.id = proximoId++;
        this.nome = nome;
    }


    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }
}
