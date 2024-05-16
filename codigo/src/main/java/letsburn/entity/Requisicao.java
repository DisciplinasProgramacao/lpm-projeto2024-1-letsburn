package letsburn.entity;

import jakarta.persistence.*;
import letsburn.model.Cliente;
import letsburn.model.Comanda;
import letsburn.model.Mesa;

import java.time.LocalDateTime;

@Entity
@Table(name = "requicao")
public class Requisicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "horario_entrada")
    private LocalDateTime horarioEntrada;
    @Column(name = "horario_saida")
    private LocalDateTime horarioSaida;
    @Column(name = "qtd_pessoas")
    private int qtdPessoas;
    private boolean ativa;

    @OneToOne
    private Mesa mesa;

    @OneToOne
    private Cliente cliente;

    @OneToOne
    private Comanda comanda;

    public Requisicao() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}

