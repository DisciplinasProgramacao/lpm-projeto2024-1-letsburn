package letsburn;

import java.util.List;

public class Pedido {
    private static int proximoId = 1;
    private int id;
    private boolean status;
    private List<Produto> produtos;

    public Pedido(List<Produto> produtos) {
        this.id = proximoId++;
        this.status = true;
        this.produtos = produtos;
    }

    public int getId() {
        return id;
    }

    public void encerrar(){
        this.status = false;
    }

    private double calculaTaxaServico(double total){
        return total * 0.1;
    }

    public double retornaTotal(){
        double total = 0;
        for (Produto produto : produtos) {
            total += produto.getPreco();
        }

       return total + calculaTaxaServico(total);
    }
}
