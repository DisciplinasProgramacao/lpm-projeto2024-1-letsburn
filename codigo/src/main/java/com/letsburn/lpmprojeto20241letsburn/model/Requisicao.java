package com.letsburn.lpmprojeto20241letsburn.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "requisicoes")
public class Requisicao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime horarioEntrada;
    private LocalDateTime horarioSaida;

    private int qtdPessoas;
    private boolean ativa;

    @OneToOne(mappedBy = "requisicao", cascade = CascadeType.ALL)
    private Mesa mesa;

    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente cliente;

    @OneToOne(cascade = CascadeType.ALL)
    private Comanda comanda;

    public Requisicao() {
    }

    public int getId() {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((horarioEntrada == null) ? 0 : horarioEntrada.hashCode());
        result = prime * result + ((horarioSaida == null) ? 0 : horarioSaida.hashCode());
        result = prime * result + qtdPessoas;
        result = prime * result + (ativa ? 1231 : 1237);
        result = prime * result + ((mesa == null) ? 0 : mesa.hashCode());
        result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
        result = prime * result + ((comanda == null) ? 0 : comanda.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Requisicao other = (Requisicao) obj;
        if (id != other.id)
            return false;
        if (horarioEntrada == null) {
            if (other.horarioEntrada != null)
                return false;
        } else if (!horarioEntrada.equals(other.horarioEntrada))
            return false;
        if (horarioSaida == null) {
            if (other.horarioSaida != null)
                return false;
        } else if (!horarioSaida.equals(other.horarioSaida))
            return false;
        if (qtdPessoas != other.qtdPessoas)
            return false;
        if (ativa != other.ativa)
            return false;
        if (mesa == null) {
            if (other.mesa != null)
                return false;
        } else if (!mesa.equals(other.mesa))
            return false;
        if (cliente == null) {
            if (other.cliente != null)
                return false;
        } else if (!cliente.equals(other.cliente))
            return false;
        if (comanda == null) {
            if (other.comanda != null)
                return false;
        } else if (!comanda.equals(other.comanda))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Requisicao [id=" + id + ", horarioEntrada=" + horarioEntrada + ", horarioSaida=" + horarioSaida
                + ", qtdPessoas=" + qtdPessoas + ", ativa=" + ativa + ", mesa=" + mesa
                + ", cliente=" + cliente + ", comanda=" + comanda + "]";
    }

}
