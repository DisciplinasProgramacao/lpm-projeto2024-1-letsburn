package letsburn;

import java.time.LocalDateTime;

public class Requisicao {

    private static int proximoId;
    private int id;
    private LocalDateTime horarioEntrada;
    private LocalDateTime horarioSaida;
    private int qtdPessoas;
    private boolean status;
    private Mesa mesa;
    private Cliente cliente;
    private Pedido pedido; 



    static {
        proximoId = 1;
    }

    private void init(Cliente cliente, int qtdPessoas){
        this.cliente = cliente;
        this.status = true;
        this.horarioEntrada = LocalDateTime.now();
        this.id = proximoId;
        proximoId++;

        if (qtdPessoas <= 0)
            this.qtdPessoas = 1;
        else if (qtdPessoas > 8)
            this.qtdPessoas = 8;
        else
            this.qtdPessoas = qtdPessoas;
    }

    /**
     * Metodo construtor para a classe Requisição. Utilizando como parametros o cliente e qtdPessoas. Gera um Id para a requisição.
     * @param cliente instancia da classe Cliente
     * @param qtdPessoas quantidade de pessoas que estão aguardando por uma Mesa
     */
    public Requisicao (Cliente cliente, int qtdPessoas){
        init(cliente, qtdPessoas);
    }

    public Requisicao(Cliente cliente, Mesa mesa, int qtdPessoas) {
        init(cliente, qtdPessoas);
        this.mesa = mesa;
        mesa.setOcupada(true);
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
     * Metodo setter para o atributo mesa. 
     * @param mesa Utiliza como parametro o novo valor do atributo mesa da requisição.
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
     * Metodo para encerrar a requisição. 
     * Grava o horario de saída, muda o status da mesa para false e o status da requisição para false.
     * Obs: verificar ocupada, em andamento e finalizada
     */
    public void encerrar(){
        horarioSaida = LocalDateTime.now();
        mesa.setOcupada(false);
        this.status = false;
    }
}

