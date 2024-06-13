package com.api.letsburn_restaurante.model;

public enum Cardapio {
    AGUA("Água", 3.0),
    COPO_DE_SUCO("Copo de suco", 2.0),
    REFRIGERANTE_ORGANICO("Refrigerante orgânico", 7.0),
    CERVEJA_VEGANA("Cerveja vegana", 9.0),
    TACA_DE_VINHO_VEGANO("Taça de vinho vegano", 18.0),
    MOQUECA_DE_PALMITO("Moqueca de Palmito", 32.0),
    FALAFEL_ASSADO("Falafel Assado", 20.0),
    SALADA_PRIMAVERA_MACARRAO_KONJAC("Salada Primavera com Macarrão Konjac", 25.0),
    ESCONDIDINHO_DE_INHAME("Escondidinho de Inhame", 18.0),
    STROGONOFF_DE_COGUMELOS("Strogonoff de Cogumelos", 35.0),
    CACAROLA_DE_LEGUMES("Caçarola de legumes", 22.0);

    private final String descricao;
    private final double preco;

    Cardapio(String descricao, double preco) {
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getNome() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }
    
}
