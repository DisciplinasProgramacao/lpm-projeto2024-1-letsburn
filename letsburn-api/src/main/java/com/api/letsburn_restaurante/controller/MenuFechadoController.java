package com.api.letsburn_restaurante.controller;

import com.api.letsburn_restaurante.dto.ResquestMenuFechado;
import com.api.letsburn_restaurante.model.ItemCardapio;
import com.api.letsburn_restaurante.model.ItemCombo;
import com.api.letsburn_restaurante.repository.ComandaRepository;
import com.api.letsburn_restaurante.repository.ItemCardapioRepository;
import com.api.letsburn_restaurante.repository.ItemComboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menuFechado")
public class MenuFechadoController {

  @Autowired
  private ItemComboRepository itemComboRepository;

  @Autowired
  private ItemCardapioRepository itemCardapioRepository;

  @Autowired
  private ComandaRepository comandaRepository;

  @PostMapping("/cadastrar")
  public ResponseEntity<ItemCombo> cadastrarMenuFechado(@RequestBody ResquestMenuFechado menuFechado) {
    List<ItemCardapio> itens = menuFechado.itens().stream()
        .map(nome -> itemCardapioRepository.findByNome(nome))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList());

    ItemCombo novoItemCombo = new ItemCombo(menuFechado.nome(), menuFechado.preco(), itens);

    ItemCombo itemComboSalvo = itemComboRepository.save(novoItemCombo);
    return ResponseEntity.ok(itemComboSalvo);
  }

  @GetMapping("/listar")
  public ResponseEntity<List<ItemCombo>> listarMenuFechado() {
    List<ItemCombo> menusFechados = itemComboRepository.findAll();
    return ResponseEntity.ok(menusFechados);
  }

  @GetMapping("/buscar/{id}")
  public ResponseEntity<ItemCombo> buscarMenuFechado(@PathVariable Long id) {
    Optional<ItemCombo> menuFechadoOpt = itemComboRepository.findById(id);
    if (menuFechadoOpt.isPresent()) {
      return ResponseEntity.ok(menuFechadoOpt.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
