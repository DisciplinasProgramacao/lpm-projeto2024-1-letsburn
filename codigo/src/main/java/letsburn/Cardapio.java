package letsburn;

import java.util.List;

public class Cardapio {
    private List<Produto> produtos;

    public Cardapio(){
        populaProdutos();
    }

    private void populaProdutos(){
        produtos.add(new Produto("Água", 3));
        produtos.add(new Produto("Copo de suco", 2));
        produtos.add(new Produto("Regrigerante orgânico", 7));
        produtos.add(new Produto("Cerveja vegana", 9));
        produtos.add(new Produto("Taça de vinho vegano", 18));
        produtos.add(new Produto("Moqueca de Palmito", 32));
        produtos.add(new Produto("Falavel Assado", 20));
        produtos.add(new Produto("Salada Primavera com  Macarraão Konjac", 25));
        produtos.add(new Produto("Escondidinho de Inhame", 18));
        produtos.add(new Produto("Strogonoff de Cogumelos", 35));
        produtos.add(new Produto("Caçarola de legumes", 22));
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
