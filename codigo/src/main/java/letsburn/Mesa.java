package letsburn;

/**
 * Classe que representa uma mesa em um restaurante.
 */
public class Mesa {

    private static int proximoId = 1;
    private int id;         // Identificador da mesa
    private int capacidade;    // Capacidade máxima da mesa
    private boolean ocupada;   // Indica se a mesa está ocupada ou não


    public Mesa(int capacidade) {
        this.id = proximoId++;
        this.ocupada = false;

        //TODO: melhorar essa logica
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
    /**
     * Getter para a capacidade da mesa.
     * @return A capacidade máxima da mesa.
     */
    public int getCapacidade() {
        return capacidade;
    }

    /**
     * Getter para verificar se a mesa está ocupada.
     * @return True se a mesa está ocupada, False caso contrário.
     */
    public boolean isOcupada() {
        return ocupada;
    }

    /**
     * Setter para definir se a mesa está ocupada ou não.
     * @param ocupada True se a mesa está ocupada, False caso contrário.
     */
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
}
