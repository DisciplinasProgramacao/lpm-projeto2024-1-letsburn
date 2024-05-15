package letsburn.entidades;

import jakarta.persistence.*;
import letsburn.entidades.ItemCardapio;

import java.util.List;

@Entity
@Table(name = "comandas")
public class Comanda {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_total")
    private Double valorTotal;

    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL)
    private List<ItemCardapio> pedidos;

    public Comanda() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemCardapio> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<ItemCardapio> pedidos) {
        this.pedidos = pedidos;
    }
}
