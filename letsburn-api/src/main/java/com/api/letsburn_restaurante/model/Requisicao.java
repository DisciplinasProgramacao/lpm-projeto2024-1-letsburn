package com.api.letsburn_restaurante.model;

import com.api.letsburn_restaurante.repository.RequisicaoRepository;
import com.api.letsburn_restaurante.service.ComandaService;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Optional;

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
        this.horarioEntrada = LocalDateTime.now();
        this.qtdPessoas = qtdPessoas;
        this.mesa = mesa;
        this.cliente = cliente;
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
        this.horarioEntrada = horarioEntrada;
    }

    public LocalDateTime getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(LocalDateTime horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public int getQtdPessoas() {
        return qtdPessoas;
    }

    public void setQtdPessoas(int qtdPessoas) {
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
        this.mesa = mesa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public void prepararRequisicao() {
        if (this.qtdPessoas <= 0) {
            this.qtdPessoas = 1;
        } else if (this.qtdPessoas > 8) {
            this.qtdPessoas = 8;
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
        this.ativa = false;
        this.horarioSaida = LocalDateTime.now();
        this.mesa.setOcupada(false);
    }

    public double calcularValorPorCliente(int qtdPessoas) {
        return this.comanda.calcularValorPorCliente(qtdPessoas);
    }
}
