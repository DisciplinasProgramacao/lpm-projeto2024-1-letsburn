package letsburn;

/**
 * Classe que representa uma mesa em um restaurante.
 */
public class Mesa {
    private String id;         
    private int capacidade;    
    private boolean ocupada;   

    /**
     * Construtor da classe Mesa.
     * @param id Identificador da mesa.
     * @param ocupada Indica se a mesa está ocupada
     */
    public Mesa(String id, boolean ocupada) {
        this.id = id;
        this.capacidade = 4; // Definindo a capacidade máxima como 4
        this.ocupada = ocupada;
    }

    /**
     * Getter para o identificador da mesa.
     * @return O identificador da mesa.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter para o identificador da mesa.
     * @param id O novo identificador da mesa.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter para a capacidade da mesa.
     * @return A capacidade máxima da mesa.
     */
    public int getCapacidade() {
        return capacidade;
    }

    /**
     * Setter para a capacidade da mesa.
     * @param capacidade A nova capacidade máxima da mesa.
     */
    public void setCapacidade(int capacidade) {
        // Você pode optar por não ter esse método setter, 
        // já que a capacidade máxima é fixa em 4
        this.capacidade = capacidade;
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
