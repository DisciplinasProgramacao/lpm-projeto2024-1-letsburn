package letsburn;

import java.time.LocalDateTime;

public class Requisicao {

    private static int proximoId;
    private int id;
    private LocalDateTime horarioEntrada;
    private LocalDateTime horarioSaida;
    private int qtdPessoas;
    private Mesa mesa; 
    //Será usado um objeto da classe Mesa
    private Cliente cliente; 
    // Será utilizado um objeto da classe Cliente

    static {
        proximoId = 1;
    }
    /**
     * Metodo construtor para a classe Requisição. Utilizando como parametros o cliente e qtdPessoas. Gera um Id para a requisição.
     * @param cliente instancia da classe Cliente
     * @param qtdPessoas quantidade de pessoas que estão aguardando por uma Mesa
     */
    public Requisicao (Cliente cliente, int qtdPessoas){

        this.cliente = cliente;
        this.qtdPessoas = qtdPessoas;
        this.horarioEntrada = LocalDateTime.now();
        this.id = proximoId;
        proximoId++;
    }

    /**
     * Metodo construtor para a classe Requisição. Sem utilização de parametros. Gera um Id para a requisição.
     */
    public Requisicao(){

        horarioEntrada = LocalDateTime.now();
        this.id = proximoId;
        proximoId++;

    }

    /**
     * Metodo Getter para o atributo Id
     * @return retorna o valor do Id da requisição.
     */
    public int getId() {
        return id;
    }
    /**
     * Metodo Getter para o atributo horarioEntrada.
     * @return retorna o valor do horário de entrada do cliente.
     */
    public LocalDateTime getHorarioEntrada() {
        return horarioEntrada;
    }
    /**
     * Metodo Getter para o atributo horarioSaida.
     * @return retorna o valor do horário de saída do cliente.
     */
    public LocalDateTime getHorarioSaida() {
        return horarioSaida;
    }
    /**
     * Metodo Getter para o atributo qtdPessoas.
     * @return retorna o valor da quantidade de pessoas para a requisição.
     */
    public int getQtdPessoas() {
        return qtdPessoas;
    }
    /**
     * Metodo Getter para o atributo Mesa.
     * @return retorna o valor do objeto mesa.
     */
    public Mesa getMesa() {
        return mesa;
    }

    /**
     * Metodo Getter para o atributo cliente
     * @return retorna o valor do objeto cliente.
     */
    public Cliente getCliente() {
        return cliente;
      
    public void encerrar(){
    }
}

