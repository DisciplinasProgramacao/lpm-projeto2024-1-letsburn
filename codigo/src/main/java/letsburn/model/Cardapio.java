package letsburn.model;

import java.util.List;

public class Cardapio {
    public final List<ItemCardapio> itensMenu;

    public Cardapio(List<ItemCardapio> itensMenu) {
        this.itensMenu = itensMenu;
    }

    public void cadastrarItem(ItemCardapio item) {
        itensMenu.add(item);
    }

    public void removerItem(ItemCardapio item) {
        itensMenu.remove(item);
    }

    public void exibirItens() {
        itensMenu.forEach(i -> System.out.println(String.format("%s %.2f%n", i.getNome(), i.getPreco())));
    }
}
