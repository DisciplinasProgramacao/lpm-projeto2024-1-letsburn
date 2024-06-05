package com.api.letsburn_restaurante.service;

import com.api.letsburn_restaurante.model.Comanda;
import com.api.letsburn_restaurante.model.ComandaCombo;
import com.api.letsburn_restaurante.model.ItemCardapio;
import com.api.letsburn_restaurante.repository.ComandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComandaComboService extends ComandaService {
    private static final double DEFAULT_VALUE = 32.0;
    private static final double TAXA = 0.10;

    public ComandaComboService(@Autowired ComandaRepository comandaRepository) {
        super(comandaRepository);
    }

    @Override
    public double calcularValorTotal(Comanda comanda) {
        if (comanda instanceof ComandaCombo) {
            return DEFAULT_VALUE + (DEFAULT_VALUE * TAXA);
        }
        return super.calcularValorTotal(comanda);
    }

}
