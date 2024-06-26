package letsburn.model;

import java.util.List;
import java.util.Optional;

public class Cardapio {
    public final List<ItemCardapio> itensCardapio;

    public Cardapio(List<ItemCardapio> itensCardapio) {
        this.itensCardapio = itensCardapio;
    }

    public void cadastrarItem(ItemCardapio item) {
        itensCardapio.add(item);
    }

    public void removerItem(ItemCardapio item) {
        itensCardapio.remove(item);
    }

    public void exibirItens() {
        itensCardapio.forEach(i -> System.out.printf("%s %.2f%n%n", i.getNome(), i.getPreco()));
    }

    public void exibirItem(String descricaoItem) {
        Optional<ItemCardapio> itemOptional = itensCardapio.stream().filter(itemCardapio -> itemCardapio.getNome().equals(descricaoItem)).findFirst();
        if(itemOptional.isPresent()) {
            ItemCardapio item = itemOptional.get();
            System.out.printf("%s %.2f%n%n", item.getNome(), item.getPreco());
        }
    }
}
