package letsburn.entidades;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "requisicoes")
public class Requisicao {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "horario_entrada")
    private LocalDateTime horarioEntrada;

    @Column(name = "horario_saida")
    private LocalDateTime horarioSaida;

    @Column(name = "qtd_pessoas")
    private int qtdPessoas;

    private boolean ativa;

    @ManyToOne
    @JoinColumn(name = "mesa_id", referencedColumnName = "id")
    private Mesa mesa;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "comanda_id", referencedColumnName = "id")
    private Comanda comanda;


}
