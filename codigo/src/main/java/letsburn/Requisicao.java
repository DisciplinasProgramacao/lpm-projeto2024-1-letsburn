package letsburn;

import java.time.LocalDateTime;
import java.util.Random;

public class Requisicao {

    private String id;
    private LocalDateTime horarioEntrada;
    private LocalDateTime horarioSaida;
    private int qtdPessoas;
    private Mesa mesa; 
    //Será usado um objeto da classe Mesa
    private Cliente cliente; 
    // Será utilizado um objeto da classe Cliente

    /**
     * Metodo construtor para a classe Requisição. Utilizando como parametros o cliente e qtdPessoas. Gera um Id para a requisição.
     * @param cliente instancia da classe Cliente
     * @param qtdPessoas quantidade de pessoas que estão aguardando por uma Mesa
     */
    public Requisicao (Cliente cliente, int qtdPessoas){

        this.cliente = cliente;
        this.qtdPessoas = qtdPessoas;
        this.horarioEntrada = LocalDateTime.now();
        
        Random random = new Random();

        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);
        int num3 = random.nextInt(10);
        int num4 = random.nextInt(10);

        this.id = "" + num1 + num2 + num3 + num4;
        System.out.println("Identificação" + this.id);
    }

    /**
     * Metodo construtor para a classe Requisição. Sem utilização de parametros. Gera um Id para a requisição.
     */
    public Requisicao(){

        horarioEntrada = LocalDateTime.now();
        Random random = new Random();

        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);
        int num3 = random.nextInt(10);
        int num4 = random.nextInt(10);

        this.id = "" + num1 + num2 + num3 + num4;
        System.out.println("Identificação" + this.id);

    }

    /**
     * Metodo Getter para o atributo Id
     * @return retorna o valor do Id da requisição.
     */
    public String getId() {
        return id;
    }
    /**
     * Metodo setter para o atributo Id. Utiliza como parametro o novo valor de Id da requisição.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Metodo Getter para o atributo horarioEntrada.
     * @return retorna o valor do horário de entrada do cliente.
     */
    public LocalDateTime getHorarioEntrada() {
        return horarioEntrada;
    }
    /**
     * Metodo setter para o atributo horarioEntrada. Utiliza como parametro o novo valor de horarioEntrada.
     */
    public void setHorarioEntrada(LocalDateTime horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }
    /**
     * Metodo Getter para o atributo horarioSaida.
     * @return retorna o valor do horário de saída do cliente.
     */
    public LocalDateTime getHorarioSaida() {
        return horarioSaida;
    }
    /**
     * Metodo setter para o atributo horarioSaida. Utiliza como parametro o novo valor de horarioSaida.
     */
    public void setHorarioSaida(LocalDateTime horarioSaida) {
        this.horarioSaida = horarioSaida;
    }
    /**
     * Metodo Getter para o atributo qtdPessoas.
     * @return retorna o valor da quantidade de pessoas para a requisição.
     */
    public int getQtdPessoas() {
        return qtdPessoas;
    }
    /**
     * Metodo setter para o atributo qtdPessoas. Utiliza como parametro o novo valor de qtdPessoas.
     */
    public void setQtdPessoas(int qtdPessoas) {
        this.qtdPessoas = qtdPessoas;
    }
    /**
     * Metodo Getter para o atributo Mesa.
     * @return retorna o valor do objeto mesa.
     */
    public Mesa getMesa() {
        return mesa;
    }
    /**
     * Metodo setter para o atributo mesa. Utiliza como parametro o novo valor de mesa.
     */
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
    /**
     * Metodo Getter para o atributo cliente
     * @return retorna o valor do objeto cliente.
     */
    public Cliente getCliente() {
        return cliente;
    }
    /**
     * Metodo setter para o atributo cliente. Utiliza como parametro o novo valor de cliente.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}

