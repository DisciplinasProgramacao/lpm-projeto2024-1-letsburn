package letsburn.model;

import java.util.ArrayList;
import java.util.List;

public class Cardapio {
    public List<ItemCardapio> itensMenu = new ArrayList<>();

    public Cardapio() {
        this.itensMenu.addAll(List.of(
                new ItemCardapio("Água", 3),
                new ItemCardapio("Copo de suco", 2),
                new ItemCardapio("Refrigerante orgânico", 7),
                new ItemCardapio("Cerveja vegana", 9),
                new ItemCardapio("Taça de vinho vegano", 18),
                new ItemCardapio("Moqueca de Palmito", 32),
                new ItemCardapio("Falavel Assado", 20),
                new ItemCardapio("Salada Primavera com Macarrão Konjac", 25),
                new ItemCardapio("Escondidinho de Inhame", 18),
                new ItemCardapio("Strogonoff de Cogumelos", 35),
                new ItemCardapio("Caçarola de legumes", 22)
        ));
    }

    public Cardapio(List<ItemCardapio> itensMenu) {
        this.itensMenu = itensMenu;
    }

    public void cadastrarItem(ItemCardapio item) {
        itensMenu.add(item);
    }

    public void removerItem(ItemCardapio item) {
        itensMenu.remove(item);
    }

    public void mostrarCardapio() {
        itensMenu.forEach(i -> System.out.println(String.format("%s %.2f%n", i.getNome(), i.getPreco())));
    }

    public ItemCardapio buscarItem(String nome) {
        return itensMenu.stream().filter(i -> i.getNome().equals(nome)).findFirst().orElse(null);
    }
}
