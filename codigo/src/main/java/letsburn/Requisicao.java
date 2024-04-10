package letsburn;

public class Requisicao {

    private final String id = "1";
    private final Cliente cliente;

    private final Mesa mesa;

    private final Integer quantidadePessoas;

    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public Integer getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public Requisicao(Cliente cliente, Mesa mesa, Integer quantidadePessoas) {
        this.cliente = cliente;
        this.mesa = mesa;
        this.quantidadePessoas = quantidadePessoas;
    }

    public Requisicao(Cliente cliente, Integer quantidadePessoas) {
        this.cliente = cliente;
        this.quantidadePessoas = quantidadePessoas;
    }

    public void encerrar(){

    }

}
