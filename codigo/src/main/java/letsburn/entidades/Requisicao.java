package letsburn.entidades;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "requisicao")
public class Requisicao {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
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

    public Requisicao(int qtdPessoas, Mesa mesa, Cliente cliente, Comanda comanda) {
        this.horarioEntrada = LocalDateTime.now();
        this.qtdPessoas = qtdPessoas;
        this.ativa = true;
        this.mesa = mesa;
        this.cliente = cliente;
        this.comanda = comanda;
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
