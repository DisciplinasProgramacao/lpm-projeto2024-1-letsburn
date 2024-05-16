package letsburn.controller;


import letsburn.model.Requisicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("requisicao")
public class RequisicaoController {

    @Autowired
    RequisicaoService requisicaoService;

    @PostMapping
    public ResponseEntity postRequisicao(@RequestBody Requisicao requisicao) {
        requisicaoService.criaRequisicao(Requisicao);
    }
    
}
