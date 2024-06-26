package com.api.letsburn_restaurante.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "requisicao")
public class Requisicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime horarioEntrada;
    private LocalDateTime horarioSaida;
    private int qtdPessoas;
    private boolean ativa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mesa_id", referencedColumnName = "id")
    private Mesa mesa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comanda_id", referencedColumnName = "id")
    private Comanda comanda;

    public Requisicao() {
    }

    public Requisicao(int qtdPessoas, Mesa mesa, Cliente cliente, boolean ativa) {
        setQtdPessoas(qtdPessoas);
        setMesa(mesa);
        setCliente(cliente);
        this.horarioEntrada = LocalDateTime.now();
        this.ativa = ativa;
        this.comanda = new Comanda();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(LocalDateTime horarioEntrada) {
        if (horarioEntrada == null) {
            throw new IllegalArgumentException("Horário de entrada não pode ser nulo.");
        }
        this.horarioEntrada = horarioEntrada;
    }

    public LocalDateTime getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(LocalDateTime horarioSaida) {
        if (horarioSaida != null && horarioSaida.isBefore(horarioEntrada)) {
            throw new IllegalArgumentException("Horário de saída não pode ser antes do horário de entrada.");
        }
        this.horarioSaida = horarioSaida;
    }

    public int getQtdPessoas() {
        return qtdPessoas;
    }

    public void setQtdPessoas(int qtdPessoas) {
        if (qtdPessoas <= 0) {
            throw new IllegalArgumentException("Quantidade de pessoas deve ser maior que zero.");
        }
        this.qtdPessoas = qtdPessoas;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        if (mesa == null) {
            throw new IllegalArgumentException("Mesa não pode ser nula.");
        }
        this.mesa = mesa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        this.cliente = cliente;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        if (comanda == null) {
            throw new IllegalArgumentException("Comanda não pode ser nula.");
        }
        this.comanda = comanda;
    }

    public void prepararRequisicao() {
        if (this.qtdPessoas <= 0) {
            throw new IllegalArgumentException("Quantidade de pessoas deve ser maior que zero.");
        } else if (this.qtdPessoas > 8) {
            throw new IllegalArgumentException("Quantidade de pessoas não pode ser maior que 8.");
        }
        this.mesa.setOcupada(true);
    }

    public void adicionarPedido(ItemCardapio item) {
        if (comanda == null) {
            comanda = new Comanda();
        }
        comanda.adicionarPedido(item);
    }

    public void fecharConta() {
        if (!this.ativa) {
            throw new IllegalStateException("A requisição já está fechada.");
        }
        this.ativa = false;
        this.horarioSaida = LocalDateTime.now();
        this.mesa.setOcupada(false);
    }

    public double calcularValorPorCliente(int qtdPessoas) {
        if (qtdPessoas <= 0) {
            throw new IllegalArgumentException("Quantidade de pessoas deve ser maior que zero.");
        }
        return this.comanda.calcularValorPorCliente(qtdPessoas);
    }
}
