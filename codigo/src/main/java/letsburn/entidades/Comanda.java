package letsburn.entidades;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "comandas")
public class Comanda {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private static final double taxa = 0.10;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comanda")
    private List<ItemCardapio> pedidos;


    public Comanda() {
    }

    public Comanda(List<ItemCardapio> pedidos) {
        this.pedidos = pedidos;
    }

    public Long getId() {
        return id;
    }

    public List<ItemCardapio> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<ItemCardapio> pedidos) {
        this.pedidos = pedidos;
    }

    public void adicionarPedido(ItemCardapio item){
        pedidos.add(item);
    }
}
