package letsburn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe que representa uma mesa em um restaurante.
 */
@Entity
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;         // Identificador da mesa
    private int capacidade; // Capacidade máxima da mesa
    private boolean ocupada; // Indica se a mesa está ocupada ou não

    public Mesa() {
        // Construtor padrão necessário para JPA
    }

    public Mesa(int capacidade) {
        this.ocupada = false;

        if (capacidade <= 0)
            this.capacidade = 4;
        else if (capacidade > 8)
            this.capacidade = 8;
        else
            this.capacidade = capacidade;
    }

    public int getId() {
        return id;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
}
