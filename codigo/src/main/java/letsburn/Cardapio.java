package letsburn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cardapio {
    private List<Produto> produtos = new ArrayList<>();

    public Cardapio(){
        populaProdutos();
    }

    private void populaProdutos(){
        produtos.addAll(Arrays.asList(
                new Produto("Água", 3),
                new Produto("Copo de suco", 2),
                new Produto("Refrigerante orgânico", 7),
                new Produto("Cerveja vegana", 9),
                new Produto("Taça de vinho vegano", 18),
                new Produto("Moqueca de Palmito", 32),
                new Produto("Falavel Assado", 20),
                new Produto("Salada Primavera com Macarrão Konjac", 25),
                new Produto("Escondidinho de Inhame", 18),
                new Produto("Strogonoff de Cogumelos", 35),
                new Produto("Caçarola de legumes", 22)
        ));
    }

    public Produto retornaProduto(int id_produtos){
        return produtos.stream().filter(p -> p.getId() == id_produtos).findFirst().orElse(null);
    }

    /**
     * Exibe o cardápio atual da LetsBurn.
     *
     * Este método imprime no console o cardápio da LetsBurn, listando os produtos disponíveis
     * com seus respectivos identificadores, nomes e preços.
     *
     * Exemplo de uso:
     * <pre>{@code
     *     exibirCardapio();
     * }</pre>
     *
     */
    public void exibirCardapio(){
        System.out.println("Cardápio LetsBurn");
        //TODO melhorar logica, sout toString de produtos
        produtos.forEach(p -> System.out.println(p.getId() + " - " + p.getNome() + " - R$ " + p.getPreco()));
    }
}
